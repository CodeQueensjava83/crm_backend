package com.generation.crm.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.generation.crm.model.Oportunidade;
import com.generation.crm.repository.OportunidadeRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/oportunidades")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OportunidadeController {

    @Autowired
    private OportunidadeRepository oportunidadeRepository;

    // READ -> buscar todas
    @GetMapping
    public ResponseEntity<List<Oportunidade>> getAll() {
        return ResponseEntity.ok(oportunidadeRepository.findAll());
    }

    // READ -> buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Oportunidade> getById(@PathVariable Long id) {
        Optional<Oportunidade> oportunidade = oportunidadeRepository.findById(id);
        return oportunidade.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // CREATE -> criar nova
    @PostMapping
    public ResponseEntity<Oportunidade> post(@Valid @RequestBody Oportunidade oportunidade) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(oportunidadeRepository.save(oportunidade));
    }

    // UPDATE -> atualizar existente
    @PutMapping
    public ResponseEntity<Oportunidade> put(@Valid @RequestBody Oportunidade oportunidade) {
        return oportunidadeRepository.findById(oportunidade.getId())
                .map(resp -> ResponseEntity.ok(oportunidadeRepository.save(oportunidade)))
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE -> deletar por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return oportunidadeRepository.findById(id)
                .map(resp -> {
                    oportunidadeRepository.deleteById(id);
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
