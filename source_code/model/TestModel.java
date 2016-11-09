package model;

import java.util.Scanner;

public class TestModel {

	public static void main(String[] args) {
		Player p1 = new Player();
		Player p2 = new Player();
		Game game = new Game(p1,p2);
		
		
		Scanner sc = new Scanner(System.in);
		while(!game.checkScores()){
			game.print();
			int house = 0;
			if(game.isPlayer1Turn()){
				//System.out.println(game.validHouses());
				do {
				System.out.print("Player 1: ");
				house = sc.nextInt();
				} while(!game.validHouses().contains(house));
			}else{
				//System.out.println(game.validHouses());
				do {
					System.out.print("Player 2: ");
					house = sc.nextInt();
					} while(!game.validHouses().contains(house - 6));
			}
			//int house = sc.nextInt();
			game.makeMove(house);
			System.out.println("\n------END OF LOOP------");
			
		}
		System.out.println("\n------GAME OVER------");

	}

}
