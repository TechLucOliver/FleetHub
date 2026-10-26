package com.fleet_hub.fleethub.controller;

import com.fleet_hub.fleethub.dto.filialDTO.FilialRequestDTO;
import com.fleet_hub.fleethub.dto.filialDTO.FilialResponseDTO;
import com.fleet_hub.fleethub.enums.StatusEntidade;
import com.fleet_hub.fleethub.model.Filial;
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

    @PutMapping("/editar/{id}")
    public ResponseEntity<FilialResponseDTO> editar(@PathVariable Long id, @Valid @RequestBody FilialRequestDTO requestDTO){
        FilialResponseDTO responseDTO = filialService.editar(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilialResponseDTO> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(filialService.buscarPorId(id));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<FilialResponseDTO>> listarPorStatus(@PathVariable StatusEntidade status){
        return ResponseEntity.ok(filialService.listarPorStatus(status));
    }

    @PatchMapping("/inativar/{id}")
    public ResponseEntity<FilialResponseDTO> inativar(@PathVariable Long id){
        FilialResponseDTO filialInativada = filialService.inativar(id);
        return ResponseEntity.ok(filialInativada);
    }

    @PatchMapping("/reativar/{id}")
    public ResponseEntity<FilialResponseDTO> reativar(@PathVariable Long id){
        FilialResponseDTO filialReativada = filialService.reativar(id);
        return ResponseEntity.ok(filialReativada);
    }

}
