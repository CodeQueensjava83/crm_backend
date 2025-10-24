package com.generation.crm.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.generation.crm.model.Oportunidade;
import com.generation.crm.repository.OportunidadeRepository;

@Service
public class OportunidadeService {
	
	@Autowired
	private OportunidadeRepository oportunidadeRepository;
	
	/**
	 * DESAFIO: Implementa a Lógica de Negócio para fechar uma Oportunidade
	 * Altera o status para '2' (Fechado)
	 * @param id o ID da Oportunidade a ser fechada
	 * @return O objeto Oportunidade atualizado
	 */
	
	public Oportunidade fecharOportunidade(Long id) {
		
		Optional<Oportunidade> oportunidadeOptional = oportunidadeRepository.findById(id);
		
		if (oportunidadeOptional.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Oportunidade não encontrada");
		}
		
		Oportunidade oportunidade = oportunidadeOptional.get();
		
		if (oportunidade.getStatus() == 2 || oportunidade.getStatus() == 3) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
					"A oportunidade já está em um status final (fecha ou perdida)");
		}
		
		oportunidade.setStatus(2); // 2 - fechado
		
		return oportunidadeRepository.save(oportunidade);
	}

}
