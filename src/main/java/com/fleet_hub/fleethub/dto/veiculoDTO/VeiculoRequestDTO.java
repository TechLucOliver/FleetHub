package com.fleet_hub.fleethub.dto.veiculoDTO;

import com.fleet_hub.fleethub.enums.CategoriaCNH;
import com.fleet_hub.fleethub.enums.CategoriaVeiculo;
import jakarta.validation.constraints.*;

public record VeiculoRequestDTO(

        @NotBlank(message = "Informar a placa é obrigatório!")
        @Size(min = 7, message = "Informe no mínimo 7 caracteres.")
        String placa,

        @NotBlank(message = "Informar a marca é obrigatório!")
        String marca,

        @NotBlank(message = "Informar o modelo é obrigatório!")
        String modelo,

        @NotNull(message = "Informar o ano de fabricação é obrigatório!")
        @Min(value = 1900, message = "O ano de fabricação deve ser válido.")
        Integer anoFabricacao,

        @NotBlank(message = "Informar o renavam é obrigatório!")
        String renavam,

        @NotNull(message = "Informar a categoria do veículo é obrigatório!")
        CategoriaVeiculo categoriaVeiculo,

        @NotNull(message = "Informar a categoria da CNH é obrigatório!")
        CategoriaCNH categoriaCNH,

        @NotNull(message = "Informar a quilometragem do odometro é obrigatório!")
        @PositiveOrZero(message = "A quilometragem não pode ser negativa")
        Long odometroKm,

        @NotNull(message = "Informar a filial é obrigatório!")
        Long filialId
) {
}
