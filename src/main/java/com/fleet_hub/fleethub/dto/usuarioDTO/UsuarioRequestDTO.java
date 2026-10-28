package com.fleet_hub.fleethub.dto.usuarioDTO;

import com.fleet_hub.fleethub.enums.CategoriaCNH;
import com.fleet_hub.fleethub.enums.TipoUsuario;
import jakarta.validation.constraints.*;

import java.time.LocalDate;


public record UsuarioRequestDTO(

        @NotBlank(message = "Informar o nome de usuário é obrigatório!")
        @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres.")
        String nome,

        @NotBlank(message = "Informar o email é obrigatório!")
        @Size(max = 100, message = "O email deve ter no máximo 100 caracteres")
        @Email(message = "O email não está no formato adequado.")
        String email,

        @NotBlank(message = "Informar a senha é obrigatório!")
        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres.")
        String senha,

        @NotNull(message = "Informar o tipo do usuário é obrigatório!")
        TipoUsuario tipoUsuario,

        @NotBlank(message = "Informar o numero da CNH é obrigatório!")
        String numeroCNH,

        @NotNull(message = "Informar a categoria é obrigatório!")
        CategoriaCNH categoriaCNH,

        @NotNull(message = "Informar a data de validade é obrigatório!")
        @Future(message = "A CNH não pode estar vencida no momento do cadastro!")
        LocalDate validadeCNH,

        @NotNull(message = "Informar o departamento associado é obrigatório!")
        Long departamentoId,

        @NotNull(message = "Informar a filial associada é obrigatório!")
        Long filialId
) {
}
