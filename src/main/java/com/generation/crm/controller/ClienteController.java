package com.generation.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin; // Importar CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping; // Importar RequestMapping
import org.springframework.web.bind.annotation.RestController; // Importar RestController

import com.generation.crm.model.Cliente;
import com.generation.crm.repository.ClienteRepository;

import jakarta.validation.Valid;

@RestController // 👈 1. ANOTAÇÃO OBRIGATÓRIA
@RequestMapping("/clientes") // 👈 2. ANOTAÇÃO OBRIGATÓRIA (URI base)
@CrossOrigin(origins = "*", allowedHeaders = "*") // 👈 3. ANOTAÇÃO OBRIGATÓRIA (Permitir acesso externo)
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	// GET ALL
	// Endpoint: GET /clientes
	@GetMapping
	public ResponseEntity<List<Cliente>> getAll() {
		return ResponseEntity.ok(clienteRepository.findAll());
	}
	
	// GET BY ID
	// Endpoint: GET /clientes/{id}
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> getById(@PathVariable Long id) { // 👈 Usar @PathVariable
		return clienteRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}
	
	// GET BY NOME (Assumindo que findByNomeContainingIgnoreCase existe no Repository)
	// Endpoint: GET /clientes/nome/{nome}
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Cliente>> getByNome(@PathVariable String nome) { // 👈 Usar @PathVariable
		return ResponseEntity.ok(clienteRepository.findByNomeContainingIgnoreCase(nome));
	}
	
	// POST - CREATE
	// Endpoint: POST /clientes
	@PostMapping
	public ResponseEntity<Cliente> post(@Valid @RequestBody Cliente cliente) {
		// Em um cenário real, você adicionaria aqui a validação de email único
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(clienteRepository.save(cliente));
	}
	
	// PUT - UPDATE
	// Endpoint: PUT /clientes
	@PutMapping
	public ResponseEntity<Cliente> put(@Valid @RequestBody Cliente cliente) {
		return clienteRepository.findById(cliente.getId())
				.map(resposta -> ResponseEntity.ok().body(clienteRepository.save(cliente)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	// DELETE
	// Endpoint: DELETE /clientes/{id}
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