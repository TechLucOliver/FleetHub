package com.fleet_hub.fleethub.service;

import com.fleet_hub.fleethub.dto.usuarioDTO.UsuarioRequestDTO;
import com.fleet_hub.fleethub.dto.usuarioDTO.UsuarioResponseDTO;
import com.fleet_hub.fleethub.model.Departamento;
import com.fleet_hub.fleethub.model.Filial;
import com.fleet_hub.fleethub.model.Usuario;
import com.fleet_hub.fleethub.repository.DepartamentoRepository;
import com.fleet_hub.fleethub.repository.FilialRepository;
import com.fleet_hub.fleethub.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final DepartamentoRepository departamentoRepository;
    private final FilialRepository filialRepository;

    @Transactional
    public UsuarioResponseDTO cadastrar(UsuarioRequestDTO requestDTO){
        if (usuarioRepository.existsByEmail(requestDTO.email())){
            throw new IllegalStateException("Já existe um usuário com o email "+requestDTO.email());
        }

        if (usuarioRepository.existsByNumeroCNH(requestDTO.numeroCNH())){
            throw new IllegalStateException("Já existe um usuário com esse número de CNH");
        }

        if (requestDTO.validadeCNH().isBefore(LocalDate.now())){
            throw new IllegalStateException("A data de vencimento não pode ser anterior a hoje");
        }

        Departamento departamentoAssociado = departamentoRepository.findById(requestDTO.departamentoId())
                .orElseThrow(() -> new IllegalStateException("Não existe um departamento com o id" + requestDTO.departamentoId()));

        Filial filialAssociada = filialRepository.findById(requestDTO.filialId())
                .orElseThrow(() -> new IllegalStateException("Não existe uma filial com o id "+requestDTO.filialId()));

        Usuario novoUsuario = Usuario.builder()
                .nome(requestDTO.nome())
                .email(requestDTO.email())
                .senhaHash(requestDTO.senha())
                .role(requestDTO.tipoUsuario())
                .numeroCNH(requestDTO.numeroCNH())
                .categoriaCNH(requestDTO.categoriaCNH())
                .validadeCNH(requestDTO.validadeCNH())
                .departamento(departamentoAssociado)
                .filial(filialAssociada)
                .build();

        Usuario usuarioSalvo = usuarioRepository.save(novoUsuario);
        return UsuarioResponseDTO.fromEntity(usuarioSalvo);
    }

    //editar
    //buscar por id
    //buscar por email
    //buscar por numeroCNH
    //editar
    //inativar
    //reativar
}
