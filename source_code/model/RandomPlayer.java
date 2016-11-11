package model;

import java.util.ArrayList;

public class RandomPlayer extends Player {

	public int nextMove(ArrayList<Integer> validHouses){
		int randomMode = (int)(Math.random() * validHouses.size()); 		
		return validHouses.get(randomMode);		
	}


}
