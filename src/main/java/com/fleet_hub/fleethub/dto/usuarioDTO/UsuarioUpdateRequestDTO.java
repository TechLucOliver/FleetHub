package com.fleet_hub.fleethub.dto.usuarioDTO;

import com.fleet_hub.fleethub.enums.CategoriaCNH;
import com.fleet_hub.fleethub.enums.TipoUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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

        @NotBlank(message = "Informar o tipo do usuário é obrigatório!")
        TipoUsuario tipoUsuario,

        @NotBlank(message = "Informar o numero da CNH é obrigatório!")
        String numeroCNH,

        @NotBlank(message = "Informar a categoria é obrigatório!")
        CategoriaCNH categoriaCNH,

        @NotBlank(message = "Informar a data de validade é obrigatório!")
        LocalDate validadeCNH
) {
}
