package com.fleet_hub.fleethub.controller;

import com.fleet_hub.fleethub.dto.usuarioDTO.UsuarioRequestDTO;
import com.fleet_hub.fleethub.dto.usuarioDTO.UsuarioResponseDTO;
import com.fleet_hub.fleethub.dto.usuarioDTO.UsuarioUpdateRequestDTO;
import com.fleet_hub.fleethub.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodos(){
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<UsuarioResponseDTO> editar(@PathVariable Long id, @Valid @RequestBody UsuarioUpdateRequestDTO requestDTO){
        UsuarioResponseDTO usuarioEditado = usuarioService.editar(id, requestDTO);
        return ResponseEntity.ok(usuarioEditado);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorEmail(@PathVariable String email){
        UsuarioResponseDTO usuarioBuscado = usuarioService.buscarPorEmail(email);
        return ResponseEntity.ok(usuarioBuscado);
    }

    @GetMapping("/cnh/{numeroCnh}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorNumeroCNH(@PathVariable String numeroCnh){
        UsuarioResponseDTO usuarioBuscado = usuarioService.buscarPorNumeroCNH(numeroCnh);
        return ResponseEntity.ok(usuarioBuscado);
    }

    @PatchMapping("/inativar/{id}")
    public ResponseEntity<UsuarioResponseDTO> inativar(@PathVariable Long id){
        UsuarioResponseDTO usuarioInativo = usuarioService.inativar(id);
        return ResponseEntity.ok(usuarioInativo);
    }

    @PatchMapping("/reativar/{id}")
    public ResponseEntity<UsuarioResponseDTO> reativar(@PathVariable Long id){
        UsuarioResponseDTO usuarioAtivo = usuarioService.reativar(id);
        return ResponseEntity.ok(usuarioAtivo);
    }
}
