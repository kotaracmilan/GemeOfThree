package com.kota.cc.services;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kota.cc.domain.Move;
import com.kota.cc.domain.Player;
import com.kota.cc.repository.MoveRepository;

@Service
public class MoveService {

	@Autowired
	private MoveRepository moveRepo;
	
	public List<Move> getAllMoves(){
		return moveRepo.findAll();
	}
	
	public Move createMove(Player player, int value, String message, boolean error) {
		Move move = new Move();
		move.setPlayer(player);
		move.setResponse(value);
		move.setTime(Instant.now());
		move.setMessage(message);
		move.setError(error);
		return move;
	}
	
	public void logMove(Move move) {
		moveRepo.save(move);
	}
}
