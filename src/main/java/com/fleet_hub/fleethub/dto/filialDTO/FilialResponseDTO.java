package com.fleet_hub.fleethub.dto.filialDTO;

import com.fleet_hub.fleethub.enums.StatusEntidade;
import com.fleet_hub.fleethub.model.Filial;

import java.time.LocalDateTime;

public record FilialResponseDTO(
        Long id,
        String razaoSocial,
        String nomeFantasia,
        String cnpj,
        String sigla,
        String cidade,
        String uf,
        StatusEntidade status,
        LocalDateTime dataCadastro
) {
    public static FilialResponseDTO fromEntity(Filial filial){
        return new FilialResponseDTO(
                filial.getId(),
                filial.getRazaoSocial(),
                filial.getNomeFantasia(),
                filial.getCnpj(),
                filial.getSigla(),
                filial.getCidade(),
                filial.getUf(),
                filial.getStatus(),
                filial.getDataCadastro()
        );
    }
}
