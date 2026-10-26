package com.fleet_hub.fleethub.repository;

import com.fleet_hub.fleethub.enums.StatusEntidade;
import com.fleet_hub.fleethub.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
    Optional<Departamento> findByNome(String nome);
    Optional<Departamento> findByStatus(StatusEntidade statusEntidade);
    boolean existsByNomeIgnoreCaseAndFilialId(String nome, Long filialId);
}
