package com.generation.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.crm.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	public List<Cliente> findByNomeContainingIgnoreCase(String nome);

}
