package com.generation.crm.repository;

import java.util.List;
import java.util.Optional; // 👈 Sugestão de import para método findByEmail

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository; // 👈 Sugestão de anotação

import com.generation.crm.model.Cliente;

@Repository 
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	public List<Cliente> findByNomeContainingIgnoreCase(String nome);
	
   
    public Optional<Cliente> findByEmail(String email);
   

}