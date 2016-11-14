package model;

import java.util.ArrayList;

public class Board {
	private ArrayList<Integer> player1;
	private ArrayList<Integer> player2;

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
	
	public Board() {
		player1 = new ArrayList<Integer>(6);
		player2 = new ArrayList<Integer>(6);

		for (int i = 0; i < 6; ++i) {
			player1.add(4);
			player2.add(4);
		}

		
	}

	public ArrayList<Integer> getPlayer1() {
		return player1;
	}

	public ArrayList<Integer> getPlayer2() {
		return player2;
	}
	
	public void setPlayer1(ArrayList<Integer> listIn)
	{
		this.player1 = listIn;
	}
	
	public void setPlayer2(ArrayList<Integer> listIn)
	{
		this.player2 = listIn;
	}
	

	public int getHouseSeeds(int house) {
		if (house < 6) {
			return player1.get(house);
		} else {
			return player2.get(house - 6);
		}
	}

	public boolean canCapture(int nextHouse) {
		//System.out.println("can capture " + nextHouse + "?");
		nextHouse = nextHouse % 12;
		if (nextHouse < 6) {
			for (int i = 0; i <= nextHouse; ++i) {
				if (player1.get(i) != 2 && player1.get(i) != 3) {
					//System.out.println("yes");
					return true;
				}
			}

			for (int i = nextHouse + 1; i < 6; ++i) {
				if (player1.get(i) > 0) {
					//System.out.println("yes");
					return true;
				}
			}
		} else {
			for (int i = 0; i <= nextHouse - 6; ++i) {
				if (player2.get(i) != 2 && player2.get(i) != 3) {
					//System.out.println("yes");
					return true;
				}
			}

			for (int i = nextHouse - 5; i < 6; ++i) {
				if (player2.get(i) > 0) {
					//System.out.println("yes");
					return true;
				}
			}
		}

		//System.out.println("no");
		return false;
	}

	public void emptyHouse(int house) {
		//System.out.println("Clearing house " + house);
		if (house < 6) {
			player1.set(house, 0);
		} else {
			player2.set(house - 6, 0);
		}
	}

	public void incrementHouse(int house) {
		if (house < 6) {
			player1.set(house, player1.get(house) + 1);
		} else {
			player2.set(house - 6, player2.get(house - 6) + 1);
		}
	}

	public void print() {

		for (int i = 5; i >= 0; i--) {
			System.out.print(" " + (6 + i) + " " + "\t");
		}
		System.out.println();
		for (int i = 5; i >= 0; i--) {
			System.out.print("[" + player2.get(i) + "]" + "\t");
		}

		System.out.println();
		System.out.println();
		for (int i = 0; i < 6; i++) {
			System.out.print("[" + player1.get(i) + "]" + "\t");
		}

		System.out.println();
		for (int i = 0; i < 6; i++) {
			System.out.print(" " + i + " " + "\t");
		}

	}


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
	
	public ArrayList<Integer> getPlayer1Without0() {
		ArrayList<Integer> validHouses = new ArrayList<Integer> ();
		for(int i = 0; i < player1.size(); ++i) {
			if(player1.get(i) != 0) {
				validHouses.add(i);
			}
		}
		return validHouses;
	}
	
	public ArrayList<Integer> getPlayer2Without0() {
		ArrayList<Integer> validHouses = new ArrayList<Integer> ();
		for(int i = 0; i < player2.size(); ++i) {
			if(player2.get(i) != 0) {
				validHouses.add(i+6);
			}
		}
		
		return validHouses;
	}

	public void clear() {
		for(int i = 0; i < 6; ++i) {
			player1.set(i, 0);
			player2.set(i, 0);
		}
		
	}

}
