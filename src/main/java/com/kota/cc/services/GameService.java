package com.kota.cc.services;

import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kota.cc.domain.Move;
import com.kota.cc.domain.Player;
import com.kota.cc.exceptions.BadMoveException;

@Service
public class GameService {


	@Value("${speci.maxnumber}")
	private Integer maxValue;
	
	@Autowired
	private MoveService moveService;
	
	private Random random;
	private int previewousMachineMove;
	private boolean gameActive;
	private boolean firstMoveMade;
	private Player firstPlayer;
	
	
	@PostConstruct
	public void init() {
		random = new Random();
	}
	
	/**
	 * To start a game, first player needs to be defined as ENUM Type PLayer
	 * @param firstPlayer -  Player Enum type
	 */
	public void defineGame(Player firstPlayer) {
		if(firstPlayer == null)
			throw new BadMoveException("Starting player must be defined");
		
		this.firstPlayer = firstPlayer;
		gameActive = true;
		previewousMachineMove = -1;
		firstMoveMade = false;
	}


	/**
	 * WHen user enters number, it will return number as int response value inside Move object
	 * @param humanMove - integer number
	 * @return Move object, with status message, error and response number
	 * @throws BadMoveException - if the value entered is not correct it will throw exception, or if player order is not correct
	 */
	public Move play(int humanMove)throws BadMoveException {
		Move move = makeMove(humanMove);
		moveService.logMove(move);
		return move;
	}
	
	///////////////////////////////////////////////////////////////
	////////////////////////HEKPER METHIDS////////////////////////

	
	private int generateNumber() {
		return random.nextInt(maxValue);
	}
	
	public int solution(int input) {
		if(input ==1)
			return 1;
		int mod = input % 3;
		int result = input;
		if(mod != 0) {
			if(mod == 1)
				result = input -1;
			if(mod == 2)
				result = input +1;
		}
			return result / 3;
	}
	
	public Move makeNextMove(int humanMove) {
		if(humanMove == 1) {
			gameActive = false;
			firstPlayer = null;
			return moveService.createMove(Player.HUMAN, humanMove, "Game over. Human won", false); 
		}

		int nextMove = solution(humanMove);
		previewousMachineMove = nextMove;
		
		if(nextMove > 1)
			return moveService.createMove(Player.MACHINE, nextMove, "", false); //log machine move
		
		if(nextMove == 1) {
			gameActive = false;
			firstPlayer = null;
			return moveService.createMove(Player.MACHINE, nextMove, "Game over. Machine won", false);
		}
		
		return null;
	}
	

	public Move makeMove(int humanMove)	throws BadMoveException {
		Move move;
		
		if(!gameActive)
			throw new BadMoveException("No players defines yet");
		
		if(humanMove < 1 && firstPlayer == Player.HUMAN && !firstMoveMade)
			throw new BadMoveException("You need to play first");
		
		if(humanMove > 0 && firstPlayer == Player.MACHINE && !firstMoveMade)
			throw new BadMoveException("Machine plays first");
		
		if(humanMove < 1 && firstPlayer == Player.MACHINE && !firstMoveMade) {
			
			int startNumber = 0;
			while(startNumber < 3)  {
				startNumber = generateNumber();
			}
			
			previewousMachineMove = startNumber;
			move = moveService.createMove(Player.MACHINE, startNumber, "Start move", false);
			firstMoveMade = true;
			return move;
		}
		
		if(humanMove > 1 && firstPlayer == Player.HUMAN && !firstMoveMade) {
			firstMoveMade = true;
			return makeNextMove(humanMove);
		}
		
		if(humanMove > 1 && firstPlayer == Player.HUMAN && !firstMoveMade) {
			firstMoveMade = true;
			return makeNextMove(humanMove);
		}
		
		if(previewousMachineMove != -1 && humanMove != solution(previewousMachineMove)  && firstMoveMade) 
			throw new BadMoveException("Not correct move");
		
		
		//MAKE NEXT MOVE
		return makeNextMove(humanMove);
	}
	
	
}
