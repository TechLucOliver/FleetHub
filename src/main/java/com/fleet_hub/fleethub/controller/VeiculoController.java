package com.fleet_hub.fleethub.controller;

import com.fleet_hub.fleethub.dto.veiculoDTO.VeiculoRequestDTO;
import com.fleet_hub.fleethub.dto.veiculoDTO.VeiculoResponseDTO;
import com.fleet_hub.fleethub.enums.CategoriaVeiculo;
import com.fleet_hub.fleethub.enums.StatusVeiculo;
import com.fleet_hub.fleethub.service.VeiculoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/veiculos")
@AllArgsConstructor
public class VeiculoController {
    private final VeiculoService veiculoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<VeiculoResponseDTO> cadastrar(@Valid @RequestBody VeiculoRequestDTO veiculoRequestDTO) {
        VeiculoResponseDTO veiculoCadastrado = veiculoService.cadastrar(veiculoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(veiculoCadastrado);
    }

    @GetMapping
    public ResponseEntity<List<VeiculoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(veiculoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(veiculoService.buscarPorId(id));
    }

    @GetMapping("/placa/{placa}")
    public ResponseEntity<VeiculoResponseDTO> buscarPorPlaca(@PathVariable String placa) {
        return ResponseEntity.ok(veiculoService.buscarPorPlaca(placa));
    }

    @GetMapping("/marca/{marca}")
    public ResponseEntity<List<VeiculoResponseDTO>> listarPorMarca(@PathVariable String marca) {
        return ResponseEntity.ok(veiculoService.listarPorMarca(marca));
    }

    @GetMapping("/modelo/{modelo}")
    public ResponseEntity<List<VeiculoResponseDTO>> listarPorModelo(@PathVariable String modelo) {
        return ResponseEntity.ok(veiculoService.listarPorModelo(modelo));
    }

    @GetMapping("/ano/{anoFabricacao}")
    public ResponseEntity<List<VeiculoResponseDTO>> listarPorAnoFabricacao(@PathVariable Integer anoFabricacao) {
        return ResponseEntity.ok(veiculoService.listarPorAnoFabricacao(anoFabricacao));
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<VeiculoResponseDTO>> listarPorCategoria(@PathVariable CategoriaVeiculo categoria) {
        return ResponseEntity.ok(veiculoService.listarPorCategoria(categoria));
    }

    @GetMapping("/status")
    public ResponseEntity<List<VeiculoResponseDTO>> listarPorStatus(@RequestParam StatusVeiculo status) {
        return  ResponseEntity.ok(veiculoService.listarPorStatus(status));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<VeiculoResponseDTO> editar(@PathVariable Long id, @RequestBody VeiculoRequestDTO veiculoRequestDTO) {
        return ResponseEntity.ok(veiculoService.editar(id, veiculoRequestDTO));
    }

    @PatchMapping("/inativar/{id}")
    public ResponseEntity<VeiculoResponseDTO> tornarInativo(@PathVariable Long id) {
        return ResponseEntity.ok(veiculoService.tornarInativo(id));
    }

    @PatchMapping("/disponibilizar/{id}")
    public ResponseEntity<VeiculoResponseDTO> tornarDisponivel(@PathVariable Long id) {
        return ResponseEntity.ok(veiculoService.tornarDisponivel(id));
    }

    @PatchMapping("/manutencao/{id}")
    public ResponseEntity<VeiculoResponseDTO> colocarEmManutencao(@PathVariable Long id) {
        return ResponseEntity.ok(veiculoService.colocarEmManutencao(id));
    }

    @PatchMapping("/uso/{id}")
    public ResponseEntity<VeiculoResponseDTO> colocarEmUso(@PathVariable Long id) {
        return ResponseEntity.ok(veiculoService.colocarEmUso(id));
    }
}
