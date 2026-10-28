package com.fleet_hub.fleethub.dto.usuarioDTO;

import com.fleet_hub.fleethub.enums.CategoriaCNH;
import com.fleet_hub.fleethub.enums.TipoUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UsuarioUpdateRequestDTO(
        @NotBlank(message = "Informar o nome de usuário é obrigatório!")
        @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres.")
        String nome,

        @NotBlank(message = "Informar o email é obrigatório!")
        @Size(max = 100, message = "O email deve ter no máximo 100 caracteres")
        @Email(message = "O email não está no formato adequado.")
        String email,

        @NotNull(message = "Informar o tipo do usuário é obrigatório!")
        TipoUsuario tipoUsuario,

        @NotNull(message = "Informar a categoria é obrigatório!")
        CategoriaCNH categoriaCNH,

        @NotNull(message = "Informar a data de validade é obrigatório!")
        LocalDate validadeCNH,

        @NotNull(message = "Informar a filial é obrigatório!")
        Long filialId,

        @NotNull(message = "Informar o departamento é obrigatório!")
        Long departamentoId
) {
}
