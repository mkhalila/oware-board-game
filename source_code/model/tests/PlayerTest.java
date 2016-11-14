package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest {

	@Test
	public void testInitialScore()
	{
		Player testPlayer = new HumanPlayer();
		int initialScore = testPlayer.getScore();
		
		//Test initial score is 0
		assertEquals(0, initialScore);
	}
	
	@Test
	public void testSetScore()
	{
		Player testPlayer = new HumanPlayer();
		
		//Test initial setting
		testPlayer.setScore(5);
		assertEquals(5, testPlayer.getScore());
		
		//Test zero setting
		testPlayer.setScore(0);
		assertEquals(0, testPlayer.getScore());
		
		//Test negative setting
		testPlayer.setScore(-5);
		assertEquals(-5, testPlayer.getScore());
	}
	
	@Test
	public void testIncreaseScore()
	{
		Player testPlayer = new HumanPlayer();
		
		//Test increase works from initial (0) value
		testPlayer.increaseScore(5);
		assertEquals(5, testPlayer.getScore());
		
		//Test increase works from a non-zero value
		testPlayer.increaseScore(10);
		assertEquals(15, testPlayer.getScore());
		
		//Test if increase works with negative value
		testPlayer.increaseScore(-5);
		assertEquals(10, testPlayer.getScore());
	}

}
