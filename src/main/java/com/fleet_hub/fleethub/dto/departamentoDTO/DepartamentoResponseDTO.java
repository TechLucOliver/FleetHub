package com.fleet_hub.fleethub.dto.departamentoDTO;

import com.fleet_hub.fleethub.enums.StatusEntidade;
import com.fleet_hub.fleethub.model.Departamento;
import com.fleet_hub.fleethub.model.Filial;
import com.fleet_hub.fleethub.model.Usuario;

import java.util.Set;

public record DepartamentoResponseDTO(
        Long id,
        String nome,
        String centroCusto,
        StatusEntidade status,
        Long filialId,
        String filialNomeFantasia
) {
    public static DepartamentoResponseDTO fromEntity(Departamento departamento){
        return new DepartamentoResponseDTO(
                departamento.getId(),
                departamento.getNome(),
                departamento.getCentroCusto(),
                departamento.getStatus(),
                departamento.getFilial() != null ? departamento.getFilial().getId() : null,
                departamento.getFilial() != null ? departamento.getFilial().getNomeFantasia() : null
        );
    }
}
