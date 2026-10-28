package com.fleet_hub.fleethub.controller;

import com.fleet_hub.fleethub.dto.veiculoDTO.VeiculoRequestDTO;
import com.fleet_hub.fleethub.dto.veiculoDTO.VeiculoResponseDTO;
import com.fleet_hub.fleethub.service.VeiculoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/veiculos")
@AllArgsConstructor
public class VeiculoController {
    private final VeiculoService veiculoService;

    @Transactional
    @PostMapping("/cadastrar")
    public ResponseEntity<VeiculoResponseDTO> cadastrar(@Valid @RequestBody VeiculoRequestDTO veiculoRequestDTO) {
        VeiculoResponseDTO veiculoCadastrado = veiculoService.cadastrar(veiculoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(veiculoCadastrado);
    }
}
