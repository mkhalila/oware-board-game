package model;
import java.lang.Math;
import java.util.ArrayList;

/**
 * Class that represents a game of Oware
 *
 */
public class Game {
    private Board board;	//The board the game is played on
    private Player player1;	//The game's first player
    private Player player2;	//The game's second player
    private boolean isFinished;	//boolean representing if the game is finished or not
    private boolean isP1Turn;
    private static final int P1WON = 1;	
    private static final int P2WON = 2;
    private static final int DRAW = 0;
    
    /**
     * Constructor that initialises a new Game and sets references
     * @param player1 the first player in the game
     * @param player2 the second player in the game
     */
    public Game(Player player1, Player player2) {
        board = new Board();
        this.player1 = player1;
        this.player2 = player2;
        player1.playGame(this);
        player2.playGame(this);
        isFinished = false;
        isP1Turn = Math.random() < 0.5;	//Randomly sets which player starts the game
    }
    
    /**
     * Constructor that creates a copy of an existing game
     * @param other the game to be copied
     */
    public Game(Game other) {
    	this.board = new Board(other.board);
    	this.player1 = other.player1;
    	this.player2 = other.player2;
    	this.isFinished = other.isFinished;
    	this.isP1Turn = other.isP1Turn;
    }

    /**
     * Method to make a valid move
     * @param house the house the move originates from
     */
    public void makeMove (int house) {
        int seeds = board.getHouseSeeds(house);
        int nextHouse = house;
        board.emptyHouse(house);

        while(seeds > 0) {
            if((++nextHouse) % 12 == house)
                continue;
            board.incrementHouse(nextHouse % 12);	//Increment the seeds in the house
            --seeds;
        }

        nextHouse %= 12;
        if(board.canCapture(nextHouse)) {
            while (
                    (nextHouse>=0) &&
                    (house / 6 != (nextHouse) / 6) && (board.getHouseSeeds(nextHouse) == 2 || board.getHouseSeeds(nextHouse) == 3) ) {

                if (isP1Turn) {
                    player1.increaseScore(board.getHouseSeeds(nextHouse));
                } else {
                    player2.increaseScore(board.getHouseSeeds(nextHouse));
                }
                board.emptyHouse(nextHouse);

                --nextHouse;
            }
        }
        isP1Turn = !isP1Turn;

    }

    /**
     * Method to check if any game-ending conditions have been met
     * @return a boolean representing the end-state of the game (true if game is finished).
     */
    public boolean checkScores() {
        if(player1.getScore() > 24 || player2.getScore() > 24 || (player1.getScore() == 24 && player2.getScore() == 24)) {
            isFinished = true;
        }
        return isFinished;
    }

    /**
     * Method to return an integer that denotes which player has won the game
     * @return an integer referring to which player has won
     */
    public int returnWinner() {
        if(player2.getScore() < player1.getScore()) {
            return P1WON;
        } else {
            if(player1.getScore() < player2.getScore()) {
                return P2WON;
            } else{
                return DRAW;
            }
        }
    }
    
    /**
     * toString method for printing the state of the game (board and players' scores
     */
    public String toString(){
    	String toReturn = "";
    	
        toReturn += "\t\tPlayer 2 (" + player2.getScore() + ")\n" + "\n";
        toReturn += board;
        toReturn += "\n\n\t\tPlayer 1 (" + player1.getScore() + ")" + "\n";
        
        return toReturn;
    }
    
    /**
     * Method to check whether it is player 1's turn
     * @return a boolean representing whether or not it is player 1's turn
     */
    public boolean isPlayer1Turn(){
        return isP1Turn;
    }
    
    /**
     * Method which returns the valid houses for each player
     * @return an arraylist containing the indexes of the valid houses
     */
    public ArrayList<Integer> validHouses () {
    	if(isP1Turn) {
    		if(board.opponentHasSeeds(2)) {    			
    			return board.getPlayer1Without0();
    		} else {
    			return board.getValidHouses(1);
    		}
    	} else {
    		if(board.opponentHasSeeds(1)) {
    			return board.getPlayer2Without0();
    		} else {
    			return board.getValidHouses(2);
    		}
    	}
    }

    /**
     * A method that implements the behaviour for when players have to capture their own seeds
     */
	public void playersCaptureOwnSeeds() {
		
		for(int i: board.getPlayer1()) {
			
			player1.setScore(player1.getScore() + i);
		}
		
		for(int i: board.getPlayer2())
			player2.setScore(player2.getScore() + i);
		
		board.clear();
		isFinished = true;
		
	}
	
	/**
	 * A Method which returns the board associated with this game
	 * @return the board object associated with this game
	 */
	public Board getBoard() {
		return board;
	}
	
	/**
	 * A method which returns the player 1 object associated with this game
	 * @return the player 1 object
	 */
	public Player getPlayer1() {
		return player1;
	}
	
	/**
	 * A method which returns the player 2 object associated with this game
	 * @return the player 2 object
	 */
	public Player getPlayer2() {
		return player2;
	}

	/**
	 * A method which returns the houses associated with player 1
	 * @return an array list containing player 1's houses
	 */
	public ArrayList<Integer> getPlayer1Houses() {
        return board.getPlayer1();
    }

	/**
	 * A method which returns the houses associated with player 2
	 * @return an array list containing player 2's houses
	 */
    public ArrayList<Integer> getPlayer2Houses() {
        return board.getPlayer2();
    }

}
