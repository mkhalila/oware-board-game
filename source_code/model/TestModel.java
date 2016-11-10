package model;

import java.util.Scanner;

public class TestModel {

	public static void main(String[] args) {
		Player p1 = new HumanPlayer();
		Player p2 = new RandomPlayer();
		Game game = new Game(p1,p2);
		
		/*
		Scanner sc = new Scanner(System.in);
		while(!game.checkScores()){
			game.print();
			
			if(game.validHouses().isEmpty()) {
				game.playersCaptureOwnSeeds();
				break;
			}
			
			int house = 0;
			if(game.isPlayer1Turn()){
				System.out.println(game.validHouses());
				do {
					System.out.print("Player 1: ");
					house = sc.nextInt();
				} while(!game.validHouses().contains(house));
			}else{
				System.out.println(game.validHouses());
				do {
					System.out.print("Player 2: ");
					house = sc.nextInt();
				} while(!game.validHouses().contains(house));
			}
			//int house = sc.nextInt();
			game.makeMove(house);
			System.out.println("\n------END OF LOOP------");
			
		}
		*/
		
		
		
		while(!game.checkScores()){
			game.print();
			
			if(game.validHouses().isEmpty()) {
				game.playersCaptureOwnSeeds();
				break;
			}
			System.out.println(game.validHouses());
			int house = 0;
			if(game.isPlayer1Turn()){
				
				do {
					System.out.print("Player 1: ");
					house = p1.nextMove(game.validHouses());
				} while(!game.validHouses().contains(house));
			}else{
				do {
					System.out.print("Player 2: ");
					house = p2.nextMove(game.validHouses());
				} while(!game.validHouses().contains(house));
			}
			//int house = sc.nextInt();
			game.makeMove(house);
			System.out.println("\n------END OF LOOP------");
			
		}
		game.print();
		System.out.println("\n------GAME OVER------");

	}

}
