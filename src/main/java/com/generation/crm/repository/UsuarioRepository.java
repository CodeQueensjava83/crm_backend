package com.generation.crm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.crm.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Pesquisa pelo campo "usuario" (email/login)
    List<Usuario> findAllByUsuarioContainingIgnoreCase(String usuario);

    // Busca um usuário exato pelo login/email
    Optional<Usuario> findByUsuario(String usuario);
}
