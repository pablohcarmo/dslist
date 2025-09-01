package com.devsuperior.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.repositories.GameRepository;

@Service
public class GameService {

	// Instancia e injeta GameRepository dentro do GameService
	@Autowired
	private GameRepository gameRepository;

	// Consulta no BD Relacional
	public List<GameMinDTO> findAll() {
		//Este método faz uma consulta no BD e retorna em uma lista chamada result
		List<Game> result = gameRepository.findAll();
		List<GameMinDTO> dto = result.stream().map(x -> new GameMinDTO(x)).toList();
		return dto;
	}
}
