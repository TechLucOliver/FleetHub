package com.fleet_hub.fleethub.service;

import com.fleet_hub.fleethub.dto.veiculoDTO.VeiculoRequestDTO;
import com.fleet_hub.fleethub.dto.veiculoDTO.VeiculoResponseDTO;
import com.fleet_hub.fleethub.model.Filial;
import com.fleet_hub.fleethub.model.Veiculo;
import com.fleet_hub.fleethub.repository.FilialRepository;
import com.fleet_hub.fleethub.repository.VeiculoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class VeiculoService {
    private final VeiculoRepository veiculoRepository;
    private final FilialRepository filialRepository;

    @Transactional
    public VeiculoResponseDTO cadastrar(VeiculoRequestDTO veiculoRequestDTO) {
        if (veiculoRepository.existsByPlacaIgnoreCase(veiculoRequestDTO.placa())){
            throw new RuntimeException("Já existe um veículo cadastrado com este placa "+veiculoRequestDTO.placa());
        }

        Filial filialAssociada = filialRepository.findById(veiculoRequestDTO.filialId())
                .orElseThrow(() -> new RuntimeException("Nenhuma filial foi encontrada com o id "+veiculoRequestDTO.filialId()));

        Veiculo novoVeiculo = Veiculo.builder()
                .placa(veiculoRequestDTO.placa())
                .marca(veiculoRequestDTO.marca())
                .modelo(veiculoRequestDTO.modelo())
                .anoFabricacao(veiculoRequestDTO.anoFabricacao())
                .renavam(veiculoRequestDTO.renavam())
                .categoria(veiculoRequestDTO.categoriaVeiculo())
                .categoriaCNH(veiculoRequestDTO.categoriaCNH())
                .odometroKm(veiculoRequestDTO.odometroKm())
                .filial(filialAssociada)
                .build();

        Veiculo veiculoCadastrado = veiculoRepository.save(novoVeiculo);
        return VeiculoResponseDTO.fromEntity(veiculoCadastrado);
    }
}
