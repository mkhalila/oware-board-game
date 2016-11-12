package model;

import java.util.ArrayList;
import java.util.Scanner;

public class AIPlayer extends Player{

	@Override
	public int nextMove(ArrayList<Integer> validHouses) {
		int max = 0;
		int house = validHouses.get(0);
		
		for(int i = 0; i < validHouses.size(); ++i) {
			Game fakeGame = new Game(game);
			
			int previousScore = getScore();
			fakeGame.makeMove(validHouses.get(i));
			int scoreAfter = getScore();
			
			int difference = scoreAfter - previousScore;
			if(difference > max) {
				max = difference;
				house = validHouses.get(i);			
			}
			
			setScore(previousScore);
		}

		System.out.println(max);
		System.out.println(house);
		return house;
	}
	//This bit was just to test if the AI Player is working
	
	/*public static void main(String[] args) {
		HumanPlayer player1 = new HumanPlayer();
		AIPlayer player2 = new AIPlayer();
		Game game = new Game(player1, player2);
		
		Scanner sc = new Scanner(System.in);
		while(!game.checkScores()){
			game.print();
			int house = -1;
			if(game.isPlayer1Turn()){
				do {
					System.out.print("Player 1: ");
					house = sc.nextInt();
				} while(!game.validHouses().contains(house));
			}else{

					System.out.print("Player 2: ");
					house = player2.nextMove(game.validHouses());
			}
			game.makeMove(house);
			System.out.println("\n------END OF LOOP------");
			
		}
		System.out.println("\n------GAME OVER------");
	}*/

}
