package com.generation.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.crm.model.Oportunidade;

@Repository
public interface OportunidadeRepository extends JpaRepository<Oportunidade, Long> {

    // Busca por descrição parcial
    List<Oportunidade> findAllByDescricaoContainingIgnoreCase(String descricao);
}
