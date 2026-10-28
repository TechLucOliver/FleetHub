package com.fleet_hub.fleethub.service;

import com.fleet_hub.fleethub.dto.departamentoDTO.DepartamentoRequestDTO;
import com.fleet_hub.fleethub.dto.departamentoDTO.DepartamentoResponseDTO;
import com.fleet_hub.fleethub.dto.departamentoDTO.DepartamentoUpdateRequestDTO;
import com.fleet_hub.fleethub.enums.StatusEntidade;
import com.fleet_hub.fleethub.model.Departamento;
import com.fleet_hub.fleethub.model.Filial;
import com.fleet_hub.fleethub.repository.DepartamentoRepository;
import com.fleet_hub.fleethub.repository.FilialRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class DepartamentoService {
    private final DepartamentoRepository departamentoRepository;
    private final FilialRepository filialRepository;

    @Transactional
    public DepartamentoResponseDTO cadastrar(DepartamentoRequestDTO requestDTO){
        boolean jaExisteNessaFilial = departamentoRepository.existsByNomeIgnoreCaseAndFilialId(requestDTO.nome(), requestDTO.filialId());

        if (jaExisteNessaFilial){
            throw new RuntimeException("Ja existe um departamento com o nome "+requestDTO.nome()+" nessa filial.");
        }

        Filial filialBuscada = filialRepository.findById(requestDTO.filialId())
                .orElseThrow(() -> new RuntimeException("Não foi encontrada nenhuma filial com o id "+requestDTO.filialId()));

        Departamento novoDepartamento = Departamento.builder()
                .nome(requestDTO.nome())
                .centroCusto(requestDTO.centroCusto())
                .filial(filialBuscada)
                .build();

        Departamento departamentoSalvo = departamentoRepository.save(novoDepartamento);
        return DepartamentoResponseDTO.fromEntity(departamentoSalvo);
    }

    @Transactional(readOnly = true)
    public List<DepartamentoResponseDTO> listarTodos(){
        return departamentoRepository.findAll()
                .stream()
                .map(DepartamentoResponseDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public DepartamentoResponseDTO buscarPorId(Long id){
        Departamento departamentoBuscado = departamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhum departamento encontrado com o id "+id));

        return DepartamentoResponseDTO.fromEntity(departamentoBuscado);
    }

    @Transactional(readOnly = true)
    public List<DepartamentoResponseDTO> listarPorStatus(StatusEntidade statusEntidade){
        return departamentoRepository.findAllByStatus(statusEntidade)
                .stream()
                .map(DepartamentoResponseDTO::fromEntity)
                .toList();
    }

    @Transactional
    public DepartamentoResponseDTO editar(Long id, DepartamentoUpdateRequestDTO requestDTO){
        Departamento departamentoBuscado = departamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhum departamento encontrado com o id "+id));

        departamentoBuscado.setNome(requestDTO.nome());
        departamentoBuscado.setCentroCusto(requestDTO.centroCusto());

        Departamento departamentoAtualizado = departamentoRepository.save(departamentoBuscado);
        return DepartamentoResponseDTO.fromEntity(departamentoAtualizado);

    }

    @Transactional
    public DepartamentoResponseDTO inativar(Long id){
        Departamento departamentoBuscado = departamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não existe um departamento com o id "+id));

        if (departamentoBuscado.getStatus() == StatusEntidade.INATIVO){
            throw new IllegalStateException("O departamento já está inativo.");
        }

        departamentoBuscado.setStatus(StatusEntidade.INATIVO);
        Departamento departamentoInativado = departamentoRepository.save(departamentoBuscado);
        return DepartamentoResponseDTO.fromEntity(departamentoInativado);
    }

    @Transactional
    public DepartamentoResponseDTO reativar(Long id){
        Departamento departamentoBuscado = departamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhum departamento encontrado com o id "+id));

        if (departamentoBuscado.getStatus() == StatusEntidade.ATIVO){
            throw new IllegalStateException("O departamento já está ativo.");
        }

        departamentoBuscado.setStatus(StatusEntidade.ATIVO);
        Departamento departamentoReativado = departamentoRepository.save(departamentoBuscado);
        return DepartamentoResponseDTO.fromEntity(departamentoReativado);
    }

}
