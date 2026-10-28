package com.fleet_hub.fleethub.service;

import com.fleet_hub.fleethub.dto.usuarioDTO.UsuarioRequestDTO;
import com.fleet_hub.fleethub.dto.usuarioDTO.UsuarioResponseDTO;
import com.fleet_hub.fleethub.dto.usuarioDTO.UsuarioUpdateRequestDTO;
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
import java.util.List;

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

    @Transactional(readOnly = true)
    public UsuarioResponseDTO buscarPorId(Long id){
        Usuario usuarioBuscado = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o id" +id));

        return UsuarioResponseDTO.fromEntity(usuarioBuscado);
    }

    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> listarTodos(){
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioResponseDTO::fromEntity)
                .toList();
    }

    @Transactional
    public UsuarioResponseDTO editar(Long id, UsuarioUpdateRequestDTO requestDTO){
        Usuario usuarioBuscado = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhum usuário encontrado com o id "+id));

        if(!usuarioBuscado.getEmail().equalsIgnoreCase(requestDTO.email()) && usuarioRepository.existsByEmail(requestDTO.email())){
            throw new RuntimeException("Já existe outro usuário cadastrado com o email "+requestDTO.email());
        }

        Filial filialDesejada = filialRepository.findById(requestDTO.filialId())
                        .orElseThrow(() -> new RuntimeException("Nenhuma filial encontrada com id "+requestDTO.filialId()));

        Departamento departamentoDesejado = departamentoRepository.findById(requestDTO.departamentoId())
                        .orElseThrow(() -> new RuntimeException("Nenhum departamento encontrado com id "+requestDTO.departamentoId()));

        if (!departamentoDesejado.getFilial().getId().equals(filialDesejada.getId())){
            throw new RuntimeException("O departamento desejado não pertence a essa filial.");
        }

        usuarioBuscado.setNome(requestDTO.nome());
        usuarioBuscado.setEmail(requestDTO.email());
        usuarioBuscado.setRole(requestDTO.tipoUsuario());
        usuarioBuscado.setCategoriaCNH(requestDTO.categoriaCNH());
        usuarioBuscado.setValidadeCNH(requestDTO.validadeCNH());
        usuarioBuscado.setFilial(filialDesejada);
        usuarioBuscado.setDepartamento(departamentoDesejado);

        Usuario usuarioEditado = usuarioRepository.save(usuarioBuscado);

        return UsuarioResponseDTO.fromEntity(usuarioEditado);

    }

    //editar



    //buscar por email
    //buscar por numeroCNH
    //editar
    //inativar
    //reativar
}
