package com.generation.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.generation.crm.model.Cliente;
import com.generation.crm.repository.ClienteRepository;

import jakarta.validation.Valid;

public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping("/clientes")
	public ResponseEntity<List<Cliente>> getAll() {
		return ResponseEntity.ok(clienteRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> getById(Long id) {
		return clienteRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Cliente>> getByNome(String nome) {
		return ResponseEntity.ok(clienteRepository.findByNomeContainingIgnoreCase(nome));
	}
	
	@PostMapping
	public ResponseEntity<Cliente> post(@Valid @RequestBody Cliente cliente) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(clienteRepository.save(cliente));
	}
	
	@PutMapping
	public ResponseEntity<Cliente> put(@Valid @RequestBody Cliente cliente) {
		return clienteRepository.findById(cliente.getId())
				.map(resposta -> ResponseEntity.ok().body(clienteRepository.save(cliente)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return clienteRepository.findById(id)
                .map(resposta -> {
                    clienteRepository.deleteById(id);
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
