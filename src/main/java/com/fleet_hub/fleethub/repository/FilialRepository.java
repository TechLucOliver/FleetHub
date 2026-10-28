package com.fleet_hub.fleethub.repository;

import com.fleet_hub.fleethub.enums.StatusEntidade;
import com.fleet_hub.fleethub.model.Filial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilialRepository extends JpaRepository<Filial, Long> {
    Optional<Filial> findByCnpj(String cnpj);
    Optional<Filial> findBySiglaIgnoreCase(String sigla);
    Optional<Filial> findAllByCidadeIgnoreCase(String cidade);
    Optional<Filial> findAllByUfIgnoreCase(String uf);
    List<Filial> findByStatus(StatusEntidade statusEntidade);
    boolean existsBySiglaIgnoreCase(String sigla);
    boolean existsByCnpjIgnoreCase(String cnpj);
}
