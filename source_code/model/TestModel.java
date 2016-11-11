package model;

public class TestModel {

	public static void main(String[] args) {
		Player p1 = new RandomPlayer();
		Player p2 = new HumanPlayer();
		Game game = new Game(p1,p2);		
		
		while(!game.checkScores()){
			game.print();
			
			if(game.validHouses().isEmpty()) {
				game.playersCaptureOwnSeeds();
				break;
			}
			System.out.println(game.validHouses());
			int house = 0;
			
			
						
			do {
				if(game.isPlayer1Turn()){
					System.out.print("Player 1: ");
					house = p1.nextMove(game.validHouses());
				}
				else {
					System.out.print("Player 2: ");
					house = p2.nextMove(game.validHouses());
				}				
			} while(!game.validHouses().contains(house));
			
			game.makeMove(house);
			System.out.println("\n------END OF TURN------");
			
		}
		game.print();
		System.out.println("\n------GAME OVER------");

	}

}
