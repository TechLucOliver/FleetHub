package com.fleet_hub.fleethub.dto.departamentoDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DepartamentoRequestDTO(
        @NotBlank(message = "O nome do departamento é obrigatório!")
        @Size(max = 100, message = "O nome deve ter no maximo 100 caracteres.")
        String nome,

        @NotBlank(message = "Informar o centro custo é obrigatório!")
        @Size(max = 15, message = "O custo do centro pode ter no máximo 15 caracteres.")
        String centroCusto,

        @NotNull(message = "O ID da filial é obrigatório!")
        Long filialId
) {
}
