package com.fleet_hub.fleethub.controller;

import com.fleet_hub.fleethub.dto.usuarioDTO.UsuarioRequestDTO;
import com.fleet_hub.fleethub.dto.usuarioDTO.UsuarioResponseDTO;
import com.fleet_hub.fleethub.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioResponseDTO> cadastrar(@Valid @RequestBody UsuarioRequestDTO requestDTO){
        UsuarioResponseDTO usuarioCadastrado = usuarioService.cadastrar(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCadastrado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable Long id){
        UsuarioResponseDTO usuarioBuscado = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(usuarioBuscado);
    }
}
