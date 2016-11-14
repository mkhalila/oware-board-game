package model;

import java.util.ArrayList;
/**
 * A class that stores the board and the houses with their contents and performs 
 * basic operations needed for the game logic
 *
 */
public class Board {
	//ArrayLists storing each player's houses
	private ArrayList<Integer> player1;
	private ArrayList<Integer> player2;

	/**
	 * Copy constructor needed for the AI Player. Copies all the fields in the house arrays for each player
	 * @param other the board to be copied
	 */
	public Board(Board other) {
		player1 = new ArrayList<Integer>(6);
		player2 = new ArrayList<Integer>(6);
		
		for(int i: other.player1) {
			player1.add(i);
		}
		
		for(int i: other.player2) {
			player2.add(i);
		}
	}
	
	/**
	 * Constructor of the class. The ArrayLists for the 2 players' houses are initialised
	 * to store 4 seeds in each
	 */
	public Board() {
		player1 = new ArrayList<Integer>(6);
		player2 = new ArrayList<Integer>(6);

		for (int i = 0; i < 6; ++i) {
			player1.add(4);
			player2.add(4);
		}

		
	}

	/**
	 * getter for player1's ArrayList of houses
	 * @return player1's ArrayList of houses
	 */
	public ArrayList<Integer> getPlayer1() {
		return player1;
	}

	/**
	 * getter for player2's ArrayList of houses
	 * @return player2's ArrayList of houses
	 */
	public ArrayList<Integer> getPlayer2() {
		return player2;
	}
	
	/**
	 * setter for player1's ArrayList of houses
	 * @param listIn the ArrayList to be set
	 */
	public void setPlayer1(ArrayList<Integer> listIn)
	{
		this.player1 = listIn;
	}
	
	/**
	 * setter for player2's ArrayList of houses
	 * @param listIn the ArrayList to be set
	 */
	public void setPlayer2(ArrayList<Integer> listIn)
	{
		this.player2 = listIn;
	}
	
	/**
	 * getter for the number of seeds in a specific house
	 * @param house 
	 * @return the number of seeds in that house
	 */
	public int getHouseSeeds(int house) {
		if (house < 6) {
			return player1.get(house);
		} else {
			return player2.get(house - 6);
		}
	}

	/**
	 * Checking if a move made from a specific house can do a capture (i.e. it won't leave
	 * the opponent with no seeds)
	 * @param nextHouse the house where the move starts
	 * @return a boolean value stating if the capture can be made or not
	 */
	public boolean canCapture(int nextHouse) {
		nextHouse = nextHouse % 12;
		if (nextHouse < 6) {
			for (int i = 0; i <= nextHouse; ++i) {
				if (player1.get(i) != 2 && player1.get(i) != 3) {
					return true;
				}
			}

			for (int i = nextHouse + 1; i < 6; ++i) {
				if (player1.get(i) > 0) {
					return true;
				}
			}
		} else {
			for (int i = 0; i <= nextHouse - 6; ++i) {
				if (player2.get(i) != 2 && player2.get(i) != 3) {
					return true;
				}
			}

			for (int i = nextHouse - 5; i < 6; ++i) {
				if (player2.get(i) > 0) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * A method to empty a house (i.e.: set the number of seeds to 0)
	 * @param house house to be emptied
	 */
	public void emptyHouse(int house) {
		if (house < 6) {
			player1.set(house, 0);
		} else {
			player2.set(house - 6, 0);
		}
	}

	/**
	 * Incrementing the number of seeds from a specific house (used in the sowing process)
	 * @param house the house to be incremented
	 */
	public void incrementHouse(int house) {
		if (house < 6) {
			player1.set(house, player1.get(house) + 1);
		} else {
			player2.set(house - 6, player2.get(house - 6) + 1);
		}
	}
	
	/**
	 * toString method to print the current state of the board
	 */
	public String toString() {
		
		String toReturn = "";

		for (int i = 5; i >= 0; i--) {
			toReturn += " " + (6 + i) + " " + "\t" + "/n";
		}
		toReturn += "\n";
		for (int i = 5; i >= 0; i--) {
			toReturn += "[" + player2.get(i) + "]" + "\t"+ "/n";
		}

		toReturn += "\n" + "\n"; 
		for (int i = 0; i < 6; i++) {
			toReturn += "[" + player1.get(i) + "]" + "\t"+ "/n";
		}

		toReturn += "\n";
		for (int i = 0; i < 6; i++) {
			toReturn += " " + i + " " + "\t"+ "/n";
		}
		
		return toReturn;

	}

	/**
	 * Method checks if the opponent still has seeds
	 * @param i the current player
	 * @return a boolean value stating if the opponent still has seeds in his houses
	 */
	public boolean opponentHasSeeds(int i) {
		switch (i) {
			case 1: {
				for(int j: player1) {
					if(j != 0) {
						return true;
					}
				}
				break;
			}
			case 2: {
				for(int j: player2) {
					if(j != 0) {
						return true;
					}
				}
				break;
			}
		}

		return false;
	}

	/**
	 * Method gets all the valid houses that a player can make a move from (excludes
	 * the houses containing 0 seeds or the ones that won't give the opponent seeds
	 * in case they don't have any)
	 * @param i the current player
	 * @return an ArrayList containing the indices of the valid houses
	 */
	public ArrayList<Integer> getValidHouses(int i) {
		ArrayList<Integer> validHouses = new ArrayList<Integer> ();
		switch (i) {
		case 1: {
			for(int j= 0; j < player1.size(); ++j) {
				if(player1.get(j) >= (6 - j)) {
					validHouses.add(j);
				}
			}
			break;
		}
		case 2: {
			for(int j= 0; j < player2.size(); ++j) {
				if(player2.get(j) >= (6 - j)) {
					validHouses.add(j + 6);
				}
			}
			break;
		}
	}
		return validHouses;
	}
	
	/**
	 * Method to get all of player1's houses that have at least 1 seed inside
	 * @return ArrayList of all the houses with at least 1 seed
	 */
	public ArrayList<Integer> getPlayer1Without0() {
		ArrayList<Integer> validHouses = new ArrayList<Integer> ();
		for(int i = 0; i < player1.size(); ++i) {
			if(player1.get(i) != 0) {
				validHouses.add(i);
			}
		}
		return validHouses;
	}
	
	/**
	 * Method to get all of player2's houses that have at least 1 seed inside
	 * @return ArrayList of all the houses with at least 1 seed
	 */
	public ArrayList<Integer> getPlayer2Without0() {
		ArrayList<Integer> validHouses = new ArrayList<Integer> ();
		for(int i = 0; i < player2.size(); ++i) {
			if(player2.get(i) != 0) {
				validHouses.add(i+6);
			}
		}
		
		return validHouses;
	}

	/**
	 * A method to clear the board (set all the houses to contain 0 seeds)
	 */
	public void clear() {
		for(int i = 0; i < 6; ++i) {
			player1.set(i, 0);
			player2.set(i, 0);
		}
		
	}

}
