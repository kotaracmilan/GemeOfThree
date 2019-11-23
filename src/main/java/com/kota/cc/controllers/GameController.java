package com.kota.cc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kota.cc.domain.Move;
import com.kota.cc.domain.Player;
import com.kota.cc.services.GameService;
import com.kota.cc.services.MoveService;

@RestController
@RequestMapping(value="/api/game")
public class GameController {

	@Autowired
	private GameService gameService;
	
	@GetMapping(value="/start/{player}")
	@CrossOrigin
	@ResponseBody
	public void startGame(@PathVariable("player") Player player) {
		gameService.defineGame(player);
	}
	
	@GetMapping(value="/restart/{player}")
	@CrossOrigin
	@ResponseBody
	public void restartGame(@PathVariable("player") Player player) {
		gameService.defineGame(player);
	}
	
	@GetMapping("/play/human/{number}")
	@CrossOrigin
	@ResponseBody
	public Move play(@PathVariable("number") int number){
		return gameService.play(number);
		
	}
	
}
