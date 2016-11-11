package model;

import java.util.ArrayList;
import java.util.Scanner;

public class HumanPlayer extends Player {

	
	public int nextMove(ArrayList<Integer> validHouses) {
		Scanner sc = new Scanner(System.in);
		int move = sc.nextInt();
		//sc.close();
		return move;
	}
	
	
}
