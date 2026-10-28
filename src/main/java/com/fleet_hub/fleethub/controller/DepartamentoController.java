package com.fleet_hub.fleethub.controller;

import com.fleet_hub.fleethub.dto.departamentoDTO.DepartamentoRequestDTO;
import com.fleet_hub.fleethub.dto.departamentoDTO.DepartamentoResponseDTO;
import com.fleet_hub.fleethub.dto.departamentoDTO.DepartamentoUpdateRequestDTO;
import com.fleet_hub.fleethub.enums.StatusEntidade;
import com.fleet_hub.fleethub.service.DepartamentoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departamentos")
@AllArgsConstructor
public class DepartamentoController {

    private final DepartamentoService departamentoService;


    @PostMapping("/cadastrar")
    public ResponseEntity<DepartamentoResponseDTO> cadastrar(@Valid @RequestBody DepartamentoRequestDTO requestDTO){
        DepartamentoResponseDTO responseDTO = departamentoService.cadastrar(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<DepartamentoResponseDTO>> listarTodos(){
        return ResponseEntity.ok(departamentoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartamentoResponseDTO> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(departamentoService.buscarPorId(id));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<DepartamentoResponseDTO>> listarPorStatus(@PathVariable StatusEntidade status){
        return ResponseEntity.ok(departamentoService.listarPorStatus(status));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<DepartamentoResponseDTO> editar(@PathVariable Long id, @Valid @RequestBody DepartamentoUpdateRequestDTO requestDTO){
        return ResponseEntity.ok(departamentoService.editar(id, requestDTO));
    }

    @PatchMapping("/inativar/{id}")
    public ResponseEntity<DepartamentoResponseDTO> inativar(@PathVariable Long id){
        return ResponseEntity.ok(departamentoService.inativar(id));
    }

    @PatchMapping("/reativar/{id}")
    public ResponseEntity<DepartamentoResponseDTO> reativar(@PathVariable Long id){
        return ResponseEntity.ok(departamentoService.reativar(id));
    }

}
