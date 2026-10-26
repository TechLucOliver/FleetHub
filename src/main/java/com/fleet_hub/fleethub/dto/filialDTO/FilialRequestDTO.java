package com.fleet_hub.fleethub.dto.filialDTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record FilialRequestDTO(

        @NotBlank(message = "Informar a razão social é obrigatório!")
        @Size(max = 100, message = "Limite de caracteres atingido.")
        String razaoSocial,

        @NotBlank(message = "Informar o nome é obrigatório!")
        @Size(max = 30, message = "Limite de caracteres atingido.")
        String nomeFantasia,

        @NotBlank(message = "Informar o cnpj é obrigatório!")
        @Size(max = 20, message = "Limite de caracteres atingido.")
        String cnpj,

        @NotBlank(message = "Informar a sigla é obrigatório")
        @Size(min = 2, max = 10, message = "Preencha corretamente o campo.")
        String sigla,

        @NotBlank(message = "Informar a cidade é obrigatório!")
        @Size(max = 20, message = "Limite de caracteres atingido.")
        String cidade,

        @NotBlank(message = "Informar a UF é obrigatório!")
        @Size(min = 2, max = 2, message = "Informe a UF corretamente")
        String uf
) {
}
