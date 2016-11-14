package model;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class representing a human player for a game of Owari
 * @author David
 *
 */

public class HumanPlayer extends Player {

	/**
	 * A method that determines the next move to be made (for console version)
	 * @param validHouses the houses that a player can select
	 * @return the index associated with the house that the player selects
	 */
	public int nextMove(ArrayList<Integer> validHouses) {
		Scanner sc = new Scanner(System.in);
		int move = sc.nextInt();
		//sc.close();
		return move;
	}
	
	
}
