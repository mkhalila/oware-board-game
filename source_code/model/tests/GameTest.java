package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

public class GameTest {

	@Test
	public void testMakeMove()
	{
		Player player1 = new HumanPlayer();
		Player player2 = new HumanPlayer();
		Game game1 = new Game(player1, player2);
		
		//Test player1 side of board after selecting house 5
		game1.makeMove(5);
		ArrayList<Integer> player2List = new ArrayList<>(6);
		player2List.add(5);
		player2List.add(5);
		player2List.add(5);
		player2List.add(5);
		player2List.add(4);
		player2List.add(4);
		
		ArrayList<Integer> player1List = new ArrayList<>(6);
		player1List.add(4);
		player1List.add(4);
		player1List.add(4);
		player1List.add(4);
		player1List.add(4);
		player1List.add(0);
		
		boolean testBoolean1 = (player2List.equals(game1.getBoard().getPlayer2()));
		boolean testBoolean2 = (player1List.equals(game1.getBoard().getPlayer1()));
		
		assertEquals(true, testBoolean1 && testBoolean2);
		
		
		//Test that making a move skips its own house 
		player1List = new ArrayList<>(Collections.nCopies(6, 0));
		player2List = new ArrayList<>(Collections.nCopies(6, 0));
		player1List.set(5, 12);
		game1.getBoard().setPlayer1(player1List);
		game1.getBoard().setPlayer2(player2List);

		
		game1.makeMove(5);
		
		ArrayList<Integer> player1Output = new ArrayList<>(Collections.nCopies(6, 1));
		ArrayList<Integer>player2Output = new ArrayList<>(Collections.nCopies(6, 1));
		player1Output.set(5,0);
		player2Output.set(0,0);
		
		testBoolean1 = (player2Output.equals(game1.getBoard().getPlayer2()));
		testBoolean2 = (player1Output.equals(game1.getBoard().getPlayer1()));
		
		assertEquals(true, testBoolean1 && testBoolean2);
	}
	
	@Test
	public void testCheckScores()
	{
		//Test if nobody has won at the start of the game
		Player player1 = new HumanPlayer();
		Player player2 = new HumanPlayer();
		Game game = new Game(player1, player2);
		
		assertEquals(false, game.checkScores());
		
		//Test no one has won when no winning conditions have been met
		player1.setScore(23);
		player2.setScore(23);
		
		assertEquals(false, game.checkScores());
		
		//Test winning condition works for player1
		player1.setScore(25);
		assertEquals(true, game.checkScores());
		
		//Test winning condition works for player2
		player1.setScore(24);
		player2.setScore(24);
		
		assertEquals(true, game.checkScores());
		
		//Test condition works for a draw
		player1.setScore(23);
		player2.setScore(25);
		assertEquals(true, game.checkScores());
		
	}
	
	@Test
	public void testReturnWinner()
	{
		Player player1 = new HumanPlayer();
		Player player2 = new HumanPlayer();
		Game game = new Game(player1, player2);
		
		//Test that player 1 is correctly identified as winner
		player1.setScore(25);
		player2.setScore(23);
		assertEquals(1, game.returnWinner());
		
		//Test that a draw is correctly identified
		player1.setScore(24);
		player2.setScore(24);
		
		assertEquals(0, game.returnWinner());
		
		//Test that player 2 is correctly identified as winner
		player1.setScore(23);
		player2.setScore(25);
		assertEquals(2, game.returnWinner());
	}
	
	@Test
	public void testPlayersCaptureOwnSeeds()
	{
		//Test that all seeds are added to the players score
		Player player1 = new HumanPlayer();
		Player player2 = new HumanPlayer();
		Game game = new Game(player1, player2);
		
		game.playersCaptureOwnSeeds();
		assertEquals(true, (player1.getScore() == 24 && player2.getScore() == 24));
	}

}
