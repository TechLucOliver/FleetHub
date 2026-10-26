package com.fleet_hub.fleethub.service;

import com.fleet_hub.fleethub.dto.departamentoDTO.DepartamentoRequestDTO;
import com.fleet_hub.fleethub.dto.departamentoDTO.DepartamentoResponseDTO;
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
        boolean nomeJaExiste = departamentoRepository.existsByNomeIgnoreCase(requestDTO.nome());

        if (nomeJaExiste){
            throw new RuntimeException("Ja existe um departamento com o nome "+requestDTO.nome());
        }

        Filial filialBuscada = filialRepository.findById(requestDTO.filialId())
                .orElseThrow(() -> new RuntimeException("Não foi encontrada nenhuma filial com o id "+requestDTO.filialId()));

        Departamento novoDepatamento = Departamento.builder()
                .nome(requestDTO.nome())
                .centroCusto(requestDTO.centroCusto())
                .filial(filialBuscada)
                .build();

        Departamento departamentoSalvo = departamentoRepository.save(novoDepatamento);
        return DepartamentoResponseDTO.fromEntity(departamentoSalvo);
    }

    @Transactional(readOnly = true)
    public List<DepartamentoResponseDTO> listarTodos(){
        return departamentoRepository.findAll()
                .stream()
                .map(DepartamentoResponseDTO::fromEntity)
                .toList();
    }

}
