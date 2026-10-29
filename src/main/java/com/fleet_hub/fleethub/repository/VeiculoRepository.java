package com.fleet_hub.fleethub.repository;

import com.fleet_hub.fleethub.enums.CategoriaVeiculo;
import com.fleet_hub.fleethub.enums.StatusVeiculo;
import com.fleet_hub.fleethub.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    Optional<Veiculo> findByPlacaIgnoreCase(String placa);
    List<Veiculo> findByMarcaIgnoreCase(String marca);
    List<Veiculo> findByModeloIgnoreCase(String modelo);
    List<Veiculo> findByAnoFabricacao(Integer anoFabricacao);
    List<Veiculo> findByCategoria(CategoriaVeiculo categoria);
    List<Veiculo> findByStatus(StatusVeiculo status);
    boolean existsByPlacaIgnoreCase(String placa);

}
