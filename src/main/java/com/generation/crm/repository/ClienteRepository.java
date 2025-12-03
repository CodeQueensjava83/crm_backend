package com.generation.crm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.crm.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // Busca clientes pelo nome
    List<Cliente> findByNomeContainingIgnoreCase(String nome);

    // Busca cliente pelo email
    Optional<Cliente> findByEmail(String email);
}
