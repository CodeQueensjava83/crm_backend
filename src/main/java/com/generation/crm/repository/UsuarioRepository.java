package com.generation.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.crm.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	public List<Usuario> findAllByUsuarioContainingIgnoreCase(String usuario);
	
}
