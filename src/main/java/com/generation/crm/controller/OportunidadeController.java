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

import com.generation.crm.model.Cliente;
import com.generation.crm.model.Oportunidade;
import com.generation.crm.model.Usuario;
import com.generation.crm.repository.ClienteRepository;
import com.generation.crm.repository.OportunidadeRepository;
import com.generation.crm.repository.UsuarioRepository;
import com.generation.crm.service.OportunidadeService;

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

    @Autowired
    private OportunidadeService oportunidadeService;

    @GetMapping
    public ResponseEntity<List<Oportunidade>> getAll() {
        return ResponseEntity.ok(oportunidadeRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Oportunidade> getById(@PathVariable Long id) {
        return oportunidadeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Oportunidade oportunidade) {

        if (oportunidade.getCliente() == null || oportunidade.getCliente().getId() == null)
            return ResponseEntity.badRequest().body("Cliente inválido");

        if (oportunidade.getUsuario() == null || oportunidade.getUsuario().getId() == null)
            return ResponseEntity.badRequest().body("Usuário inválido");

        Optional<Cliente> cliente = clienteRepository.findById(oportunidade.getCliente().getId());
        Optional<Usuario> usuario = usuarioRepository.findById(oportunidade.getUsuario().getId());

        if (cliente.isEmpty()) return ResponseEntity.badRequest().body("Cliente não encontrado");
        if (usuario.isEmpty()) return ResponseEntity.badRequest().body("Usuário não encontrado");

        // Garantir que status inicial seja 1 (Aberta)
        oportunidade.setStatus(1);

        return ResponseEntity.status(HttpStatus.CREATED).body(oportunidadeRepository.save(oportunidade));
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody Oportunidade oportunidade) {

        if (!oportunidadeRepository.existsById(oportunidade.getId()))
            return ResponseEntity.notFound().build();

        if (oportunidade.getCliente() == null || oportunidade.getCliente().getId() == null)
            return ResponseEntity.badRequest().body("Cliente inválido");

        if (oportunidade.getUsuario() == null || oportunidade.getUsuario().getId() == null)
            return ResponseEntity.badRequest().body("Usuário inválido");

        if (!clienteRepository.existsById(oportunidade.getCliente().getId()))
            return ResponseEntity.badRequest().body("Cliente não encontrado");

        if (!usuarioRepository.existsById(oportunidade.getUsuario().getId()))
            return ResponseEntity.badRequest().body("Usuário não encontrado");

        return ResponseEntity.ok(oportunidadeRepository.save(oportunidade));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return oportunidadeRepository.findById(id)
                .map(r -> {
                    oportunidadeRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Fechar oportunidade (status = 2)
    @PutMapping("/fechar/{id}")
    public ResponseEntity<Oportunidade> fechar(@PathVariable Long id) {
        return ResponseEntity.ok(oportunidadeService.fecharOportunidade(id));
    }
}

