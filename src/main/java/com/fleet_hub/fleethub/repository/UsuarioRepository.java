package com.fleet_hub.fleethub.repository;

import com.fleet_hub.fleethub.enums.CategoriaCNH;
import com.fleet_hub.fleethub.enums.StatusEntidade;
import com.fleet_hub.fleethub.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNomeIgnoreCase(String nome);
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByNumeroCNH(String numeroCNH);
    List<Usuario> findByCategoriaCNH(CategoriaCNH categoria);
    List<Usuario> findByStatus(StatusEntidade status);
    boolean existsByEmail(String email);
    boolean existsByNumeroCNH(String numeroCNH);
}
