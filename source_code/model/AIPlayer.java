package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
/**
 * A class that describes how an AI Player would chose its next move
 */
public class AIPlayer extends Player{

	@Override
	/**
	 * A method that chooses the house for an AI player, by considering both how many 
	 * seeds the player would capture with a specific move and what possibilities this
	 * would offer to the opponent. 
	 * Then, assuming this move will be made, it 
	 * 
	 * @param validHouses a list of the houses that are valid to make a move from
	 * @return the house selected
	 */
	public int nextMove(ArrayList<Integer> validHouses) {
		int max = Integer.MIN_VALUE;
		int house = validHouses.get(0);
		
		/* loops through all the valid moves and for each 
			calculates the number of seeds that would be captured. */
		for(int i = 0; i < validHouses.size(); ++i) {
			Game fakeGame = new Game(game);
			
			int previousScore = getScore();
			fakeGame.makeMove(validHouses.get(i));
			int scoreAfter = getScore();
			
			int maxOpponent = 0;
			
			/*calculates the best move that the opponent could do in this case*/
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
			
			/* calculates a difference between how the scores will look like after doing
			 * this move and finds the maximum
			 */
			int difference = scoreAfter - previousScore - maxOpponent;
			if(difference > max) {
				max = difference;
				house = validHouses.get(i);
			}
			setScore(previousScore);
		}

		return house;
	}
	

}
