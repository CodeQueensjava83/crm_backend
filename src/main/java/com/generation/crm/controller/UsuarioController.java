package com.generation.crm.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.crm.model.Usuario;
import com.generation.crm.repository.UsuarioRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<List<Usuario>> getAll() {
        return ResponseEntity.ok(usuarioRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable Long id) {
        return usuarioRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{usuario}")
    public ResponseEntity<List<Usuario>> getByUsuario(@PathVariable String usuario) {
        return ResponseEntity.ok(usuarioRepository.findAllByUsuarioContainingIgnoreCase(usuario));
    }

    @PostMapping
    public ResponseEntity<Usuario> create(@Valid @RequestBody Usuario user) {

        if (usuarioRepository.findByUsuario(user.getUsuario()).isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioRepository.save(user));
    }

    @PutMapping
    public ResponseEntity<Usuario> update(@Valid @RequestBody Usuario usuario) {

        Optional<Usuario> existente = usuarioRepository.findByUsuario(usuario.getUsuario());

        if (existente.isPresent() && !existente.get().getId().equals(usuario.getId())) {
            return ResponseEntity.badRequest().build();
        }

        return usuarioRepository.findById(usuario.getId())
                .map(r -> ResponseEntity.ok(usuarioRepository.save(usuario)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return usuarioRepository.findById(id)
                .map(r -> {
                    usuarioRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
