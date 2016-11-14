package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

public class BoardTest {

	@Test
	public void testInitialBoard()
	{
		Board testBoard = new Board();
		ArrayList<Integer> testList = new ArrayList<>();
		for (int i = 0; i < 6; ++i)
		{
			testList.add(4);
		}
		
		//Test player 1
		assertEquals(testList, testBoard.getPlayer1());
		
		//Test player 2
		assertEquals(testList, testBoard.getPlayer2());
	}
	
	@Test
	public void testGetHouseSeeds()
	{
		Board testBoard = new Board();
		
		//Test if index 0 holds 4 (Player 1's first house)
		assertEquals(4, testBoard.getHouseSeeds(0));
		
		//Test if index 6 holds 4 (Player 2's first house)
		assertEquals(4, testBoard.getHouseSeeds(6));
		
		//Test negative index
		assertEquals(4, testBoard.getHouseSeeds(-10));
		
		//Test index greater than 11
		assertEquals(4, testBoard.getHouseSeeds(15));
	}
	
	@Test
	public void testEmptyHouse()
	{
		Board testBoard = new Board();
		
		//Test clear house 0 works
		testBoard.emptyHouse(0);
		assertEquals(0, testBoard.getHouseSeeds(0));
		
		//Test clear house 6 works
		testBoard.emptyHouse(6);
		assertEquals(0, testBoard.getHouseSeeds(6));
		
		//Test negative index
		testBoard.emptyHouse(-1);
		assertEquals(0, testBoard.getHouseSeeds(-1));
		
		//Test greater than 11 index
		testBoard.emptyHouse(15);
		assertEquals(0, testBoard.getHouseSeeds(15));
	}
	
	@Test
	public void testIncrementHouse()
	{
		Board testBoard = new Board();
		
		//Test increment house 0 works
		testBoard.incrementHouse(0);
		assertEquals(5, testBoard.getHouseSeeds(0));
		
		//Test increment house 6 works
		testBoard.incrementHouse(6);
		assertEquals(5, testBoard.getHouseSeeds(6));
		
		//Test negative index
		testBoard.incrementHouse(-1);
		assertEquals(5, testBoard.getHouseSeeds(-1));
		
		//Test greater than 11 index
		testBoard.incrementHouse(15);
		assertEquals(5, testBoard.getHouseSeeds(15));
	}
	
	@Test
	public void testOpponentHasSeeds()
	{
		Board testBoard = new Board();
		
		//Test Player1
		assertEquals(true, testBoard.opponentHasSeeds(1));
		
		//Test Player2
		assertEquals(true, testBoard.opponentHasSeeds(2));
		
		testBoard.clear();
		
		//Test Player1 again
		assertEquals(false, testBoard.opponentHasSeeds(1));
		
		//Test Player2 again
		assertEquals(false, testBoard.opponentHasSeeds(2));
	}
	
	@Test
	public void testGetValidHouses()
	{
		Board testBoard = new Board();
		ArrayList<Integer> testList1 = new ArrayList<>();
		ArrayList<Integer> testList1Player2 = new ArrayList<>();
		for (int i = 2; i < 6; ++i)
		{
			testList1.add(i);
		}
		
		for (int i = 8; i < 12; ++i)
		{
			testList1Player2.add(i);
		}
		
		ArrayList<Integer> testList2 = new ArrayList<>(Collections.nCopies(6, 0));
		testBoard.setPlayer2(testList2);
		
		//Test player 1 houses
		assertEquals(testList1, testBoard.getValidHouses(1));
		
		//Test player 2 houses
		ArrayList<Integer> testList3 = new ArrayList<>(Collections.nCopies(6, 4));
		testBoard.setPlayer1(testList2);
		testBoard.setPlayer2(testList3);

		assertEquals(testList1Player2, testBoard.getValidHouses(2));	
	}
	
	@Test
	public void testCanCapture()
	{
		Board testBoard = new Board();
		ArrayList<Integer> testList1 = new ArrayList<>(Collections.nCopies(6, 0));
		boolean testBool = testBoard.canCapture(0);
		
		//Test an initial move can capture
		assertEquals(true, testBool);
		
		//Test a specific situation
		testList1.set(1, 2);
		testList1.set(0, 2);
		testBoard.setPlayer1(testList1);
		boolean testBool2 = testBoard.canCapture(1);
		
		assertEquals(false, testBool2);
		
		//Test another specific scenario
		ArrayList<Integer> testList2 = new ArrayList<>(Collections.nCopies(6, 0));
		testList2.set(0,  1);
		testList2.set(1,  2);
		testBoard.setPlayer1(testList2);
		boolean testBool3 = testBoard.canCapture(1);
		
		assertEquals(true, testBool3);
		
		
	}
	
	@Test
	public void testPlayersWithout0()
	{
		Board testBoard = new Board();
		ArrayList<Integer> testList = new ArrayList<>();
		ArrayList<Integer> testList2 = new ArrayList<>();
		for (int i = 0; i < 6; ++i)
		{
			testList.add(i);
		}
		
		for (int i = 6; i < 12; ++i)
		{
			testList2.add(i);
		}
		
		//Test initial board for player1
		assertEquals(testList, testBoard.getPlayer1Without0());
		
		//Test initial board for player2
		assertEquals(testList2, testBoard.getPlayer2Without0());
		
		//Remove house 2 from list
		testList.remove(2);
		testBoard.emptyHouse(2); //Empty house 2
		testBoard.emptyHouse(8); //Empty house 8 (equivalent house)
		testList2.remove(2);
		
		//Test board with value removed for player1
		assertEquals(testList, testBoard.getPlayer1Without0());
		
		//Test board with value removed for player2
		assertEquals(testList2, testBoard.getPlayer2Without0());
	}
	
	@Test
	public void testClear()
	{
		Board testBoard = new Board();
		ArrayList<Integer> emptyList = new ArrayList<>();
		
		//Test the empty list against player 1, should fail
		assertEquals("Should fail", emptyList, testBoard.getPlayer1());
		
		//Test the empty list against player 2, should fail
		assertEquals("Should fail", emptyList, testBoard.getPlayer2());
		
		testBoard.clear();
		
		//Test the empty list against player 1, should fail
		assertEquals("Should pass", emptyList, testBoard.getPlayer1());
		
		//Test the empty list against player 2, should fail
		assertEquals("Should pass", emptyList, testBoard.getPlayer2());
	}

}
