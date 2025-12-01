package com.generation.crm.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.crm.model.Oportunidade;
import com.generation.crm.repository.ClienteRepository;
import com.generation.crm.repository.OportunidadeRepository;
import com.generation.crm.repository.UsuarioRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/oportunidades")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OportunidadeController {

    @Autowired
    private OportunidadeRepository oportunidadeRepository;
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    // READ -> buscar todas
    @GetMapping
    public ResponseEntity<List<Oportunidade>> getAll() {
        return ResponseEntity.ok(oportunidadeRepository.findAll());
    }

    // READ -> buscar  ID
    @GetMapping("/{id}")
    public ResponseEntity<Oportunidade> getById(@PathVariable Long id) {
        Optional<Oportunidade> oportunidade = oportunidadeRepository.findById(id);
        return oportunidade.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<List<Oportunidade>> getByDescricao(@PathVariable String descricao) {
    	return ResponseEntity.ok(oportunidadeRepository.findAllByDescricaoContainingIgnoreCase(descricao));
    }

    // CREATE -> criar nova oport
    @PostMapping
    public ResponseEntity<Oportunidade> post(@Valid @RequestBody Oportunidade oportunidade) {
    	if (clienteRepository.existsById(oportunidade.getCliente().getId()) && 
    			usuarioRepository.existsById(oportunidade.getUsuario().getId())) {
    		
    		Oportunidade novaOportunidade = oportunidadeRepository.save(oportunidade);

            return oportunidadeRepository.findById(novaOportunidade.getId())
                .map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
                .orElse(ResponseEntity.status(HttpStatus.CREATED).body(novaOportunidade)); // fallback
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
            "Cliente ou Usuário ID's inválidos para o relacionamento.");
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
