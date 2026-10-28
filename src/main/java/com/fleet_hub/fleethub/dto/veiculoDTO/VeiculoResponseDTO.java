package com.fleet_hub.fleethub.dto.veiculoDTO;

import com.fleet_hub.fleethub.enums.CategoriaCNH;
import com.fleet_hub.fleethub.enums.CategoriaVeiculo;
import com.fleet_hub.fleethub.enums.StatusVeiculo;
import com.fleet_hub.fleethub.model.Veiculo;

import java.time.LocalDateTime;

public record VeiculoResponseDTO(
        Long id,
        String placa,
        String marca,
        String modelo,
        Integer anoFabricacao,
        String renavam,
        CategoriaVeiculo categoriaVeiculo,
        CategoriaCNH categoriaCNH,
        Long odometroKm,
        StatusVeiculo statusVeiculo,
        LocalDateTime dataCadastro,
        Long filialId
) {
    public static VeiculoResponseDTO fromEntity(Veiculo veiculo) {
        return new VeiculoResponseDTO(
                veiculo.getId(),
                veiculo.getPlaca(),
                veiculo.getMarca(),
                veiculo.getModelo(),
                veiculo.getAnoFabricacao(),
                veiculo.getRenavam(),
                veiculo.getCategoria(),
                veiculo.getCategoriaCNH(),
                veiculo.getOdometroKm(),
                veiculo.getStatus(),
                veiculo.getDataCadastro(),
                veiculo.getFilial().getId()
        );
    }
}
