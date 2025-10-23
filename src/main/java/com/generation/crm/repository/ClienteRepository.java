package com.generation.crm.repository;

import java.util.List;
import java.util.Optional; // 👈 Sugestão de import para método findByEmail

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository; // 👈 Sugestão de anotação

import com.generation.crm.model.Cliente;

@Repository // 👈 Recomendado para legibilidade e escaneamento do componente
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	public List<Cliente> findByNomeContainingIgnoreCase(String nome);
	
    // MÉTODOS ADICIONAIS RECOMENDADOS:
    
    // 1. Busca por e-mail (usando Optional, pois o e-mail é unique)
    public Optional<Cliente> findByEmail(String email);
    
    // 2. Busca por e-mail, ignorando maiúsculas/minúsculas
    public Optional<Cliente> findByEmailIgnoreCase(String email);

}