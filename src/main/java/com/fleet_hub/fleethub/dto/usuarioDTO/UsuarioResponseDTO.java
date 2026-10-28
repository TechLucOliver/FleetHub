package com.fleet_hub.fleethub.dto.usuarioDTO;

import com.fleet_hub.fleethub.enums.CategoriaCNH;
import com.fleet_hub.fleethub.enums.StatusEntidade;
import com.fleet_hub.fleethub.enums.TipoUsuario;
import com.fleet_hub.fleethub.model.Usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        TipoUsuario role,
        String numeroCNH,
        CategoriaCNH categoriaCNH,
        LocalDate validadeCNH,
        StatusEntidade status,
        LocalDateTime dataCadastro,
        Long filialId,
        Long departamentoId
) {
    public static UsuarioResponseDTO fromEntity(Usuario usuario){
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getRole(),
                usuario.getNumeroCNH(),
                usuario.getCategoriaCNH(),
                usuario.getValidadeCNH(),
                usuario.getStatus(),
                usuario.getDataCadastro(),
                usuario.getFilial().getId(),
                usuario.getDepartamento().getId()
        );
    }
}
