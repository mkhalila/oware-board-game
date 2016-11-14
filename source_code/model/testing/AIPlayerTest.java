package model.testing;
import model.*;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

public class AIPlayerTest {

	@Test
	public void testAIPlayerMove()
	{
		HumanPlayer player1 = new HumanPlayer();
		AIPlayer player2 = new AIPlayer();
		Game game = new Game(player1, player2);
		
		//testing optimal move
		ArrayList<Integer> player1Houses = new ArrayList<Integer>(Collections.nCopies(6,1));
		player1Houses.set(4,0);
		
		ArrayList<Integer> player2Houses = new ArrayList<Integer>(Collections.nCopies(6,4));
		
		
		game.getBoard().setPlayer1(player1Houses);
		game.getBoard().setPlayer2(player2Houses);
		
		assertEquals(11,player2.nextMove(game.validHouses()));
		
		//testing AI player doesn't capture all seeds
		player1Houses = new ArrayList<Integer>(Collections.nCopies(6,1));
		player1Houses.set(4,0);
		player1Houses.set(5,0);
		
		player2Houses = new ArrayList<Integer>(Collections.nCopies(6,4));
		
		game.getBoard().setPlayer1(player1Houses);
		game.getBoard().setPlayer2(player2Houses);
		
		assertEquals(10,player2.nextMove(game.validHouses()));
		
		//testing AI player gives opponent seeds
		player1Houses = new ArrayList<Integer>(Collections.nCopies(6,0));
		
		player2Houses = new ArrayList<Integer>(Collections.nCopies(6,0));
		player2Houses.set(0, 4);
		player2Houses.set(1, 4);
		player2Houses.set(2, 4);

		
		game.getBoard().setPlayer1(player1Houses);
		game.getBoard().setPlayer2(player2Houses);
				
		assertEquals(8,player2.nextMove(game.validHouses()));
		
		
	}

}
