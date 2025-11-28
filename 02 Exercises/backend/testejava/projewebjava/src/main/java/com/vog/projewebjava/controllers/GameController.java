package com.vog.projewebjava.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vog.projewebjava.dto.GameDTO;
import com.vog.projewebjava.entities.Game;
import com.vog.projewebjava.repositories.GameRepository;

@RestController
@RequestMapping(value = "/games")
public class GameController {

	@Autowired
	public GameRepository gameRepository;
	
	@GetMapping
	public List<GameDTO> getAllGame(){
		List<Game> result = gameRepository.findAll();
		List<GameDTO> dto = result.stream().map(x -> new GameDTO(x)).toList();
		return dto;
	}
	
	@GetMapping(value="/{id}")
	public Game getByGameId(@PathVariable Long id){
		return gameRepository.findById(id).get();
	}
}