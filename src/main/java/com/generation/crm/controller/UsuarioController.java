package com.generation.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public ResponseEntity<List<Usuario>> findAll() {
		return ResponseEntity.ok(usuarioRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getById(@PathVariable Long id) {
		return usuarioRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/usuario/{usuario}")
	public ResponseEntity<List<Usuario>> getAllByUsuario(@PathVariable String usuario) {
		return ResponseEntity.ok(usuarioRepository.findAllByUsuarioContainingIgnoreCase(usuario));
	}
	
	@PostMapping("/cadastrar")
    public ResponseEntity<Usuario> create(@Valid @RequestBody Usuario user) {
        if (usuarioRepository.findByUsuario(user.getUsuario()).isEmpty()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(user));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
	
    @PutMapping("/atualizar")
    public ResponseEntity<Usuario> update(@Valid @RequestBody Usuario usuario) {
        return usuarioRepository.findById(usuario.getId())
                .map(resposta -> {
               
                    return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.save(usuario));
                })
                .orElse(ResponseEntity.notFound().build());
        }


}
