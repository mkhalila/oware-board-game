package model;

import java.util.ArrayList;

/**
 * An abstract class representing a player in a game of Oware
 *
 */

public abstract class Player {

    private int score;
    protected Game game;
    
    /**
     * A Constructor that initialises a new player object, with a score of 0.
     */
    public Player () {
         score = 0;
    }

    /**
     * Method to get the score associated with a player
     * @return the player's score
     */
    public int getScore() {
        return score;
    }

    /**
     * Method to set the score of a player
     * @param score the new score value
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Abstract method that determines the next move to be made by a random or AI player
     * @param validHouses the houses from which a move can be made
     * @return an integer representing the index of the house which will be selected
     */
    public abstract int nextMove(ArrayList<Integer> validHouses);

    /**
     * Method to increase the players score by a specific amount
     * @param houseSeeds the amount to which to increase the score by
     */
    public void increaseScore(int houseSeeds) {
        score += houseSeeds;
    }
    
    /**
     * Method to associate game to the player (for AI purposes)
     * @param game the game the player is playing in
     */
    public void playGame(Game game) {
    	this.game = game;
    }

}

