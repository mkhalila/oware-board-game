package model;

import java.util.ArrayList;

/**
 * A class representing a player that makes random moves in game of Owari
 * @author David
 *
 */

public class RandomPlayer extends Player {

	/**
	 * Method that determines the next move to be played by the player
	 * @param validHouses the houses which the player can select
	 * @return an index representing the house which will be selected
	 */
	public int nextMove(ArrayList<Integer> validHouses){
		int randomMode = (int)(Math.random() * validHouses.size());
		System.out.println("Random player: " + validHouses.get(randomMode));
		return validHouses.get(randomMode);		
	}


}
