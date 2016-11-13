package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class AIPlayer extends Player{

	@Override
	public int nextMove(ArrayList<Integer> validHouses) {
		int max = Integer.MIN_VALUE;
		int house = validHouses.get(0);
		
		for(int i = 0; i < validHouses.size(); ++i) {
			Game fakeGame = new Game(game);
			
			int previousScore = getScore();
			fakeGame.makeMove(validHouses.get(i));
			int scoreAfter = getScore();
			
			int maxOpponent = 0;
			for(int j = 0; j < fakeGame.validHouses().size(); ++j) {
				int previousOpponentScore = fakeGame.getPlayer1().getScore();
				Game fakeOpponentGame = new Game(fakeGame);
				fakeOpponentGame.makeMove(fakeOpponentGame.validHouses().get(j));
				int opponentScoreAfter = fakeGame.getPlayer1().getScore();
				
				int opponentDifference = opponentScoreAfter - previousOpponentScore;
				if(opponentDifference > maxOpponent)
					maxOpponent = opponentDifference;
				
				fakeGame.getPlayer1().setScore(previousOpponentScore);
				
			}
			
			int difference = scoreAfter - previousScore - maxOpponent;
			if(difference > max) {
				max = difference;
				house = validHouses.get(i);
			}
			setScore(previousScore);
		}

		return house;
	}
	//This bit was just to test if the AI Player is working
	
	public static void main(String[] args) {
		HumanPlayer player1 = new HumanPlayer();
		AIPlayer player2 = new AIPlayer();
		Game game = new Game(player1, player2);
		
		ArrayList<Integer> player1Houses = new ArrayList<Integer>(Collections.nCopies(6,1));
		player1Houses.set(4,0);
		//player1Houses.set(5,0);
		
		ArrayList<Integer> player2Houses = new ArrayList<Integer>(Collections.nCopies(6,4));
		
		game.getBoard().setPlayer1(player1Houses);
		game.getBoard().setPlayer2(player2Houses);
		
		System.out.println(player2.nextMove(game.validHouses()));
	}

}
