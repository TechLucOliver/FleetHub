package com.fleet_hub.fleethub.controller;

import com.fleet_hub.fleethub.dto.filialDTO.FilialRequestDTO;
import com.fleet_hub.fleethub.dto.filialDTO.FilialResponseDTO;
import com.fleet_hub.fleethub.service.FilialService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/filial")
@AllArgsConstructor
public class FilialController {

    private final FilialService filialService;

    @PostMapping("/cadastrar")
    public ResponseEntity<FilialResponseDTO> cadastrar(@Valid @RequestBody FilialRequestDTO requestDTO){
        FilialResponseDTO responseDTO = filialService.cadastrar(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<FilialResponseDTO>> listarTodos(){
        return ResponseEntity.ok(filialService.listarTodos());
    }

}
