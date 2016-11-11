package model;
import java.lang.Math;
import java.util.ArrayList;

public class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private boolean isFinished;
    private boolean isP1Turn;
    private static final int P1WON = 1;
    private static final int P2WON = 2;
    private static final int DRAW = 0;

    public Game(Player player1, Player player2) {
        board = new Board();
        this.player1 = player1;
        this.player2 = player2;
        isFinished = false;
        isP1Turn = Math.random() < 0.5;
    }

    public void makeMove (int house) {
        int seeds = board.getHouseSeeds(house);
        int nextHouse = house;
        board.emptyHouse(house);

        while(seeds > 0) {
            if((++nextHouse) % 12 == house)
                continue;
            board.incrementHouse(nextHouse % 12);
            --seeds;
        }

        nextHouse %= 12;
        if(board.canCapture(nextHouse)) {
            while (
                    //by the way i was totally wrong about & and &&, it works exactly the other way round =)
                    /*new condition, could be done better probably*/(nextHouse>=0) &&
                    (house / 6 != (nextHouse) / 6) && (board.getHouseSeeds(nextHouse) == 2 || board.getHouseSeeds(nextHouse) == 3) ) {
                System.out.println("Capturing " + board.getHouseSeeds(nextHouse) + " seeds from house " + nextHouse);
                if (isP1Turn) {
                    player1.increaseScore(board.getHouseSeeds(nextHouse));
                } else {
                    player2.increaseScore(board.getHouseSeeds(nextHouse));
                }
                board.emptyHouse(nextHouse);
                //this is where it goes to negative index
                --nextHouse;
            }
        }
        System.out.println("end of turn");
        isP1Turn = !isP1Turn;

    }

    public boolean checkScores() {
        if(player1.getScore() > 24 || player2.getScore() > 24 || (player1.getScore() == 24 && player2.getScore() == 24)) {
            isFinished = true;
        }
        return isFinished;
    }

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
    
    public void print(){
        System.out.println("\t\tPlayer 2 (" + player2.getScore() + ")\n");
        board.print();
        System.out.println("\n\n\t\tPlayer 1 (" + player1.getScore() + ")");
    }
    
    public boolean isPlayer1Turn(){
        return isP1Turn;
    }
    
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

	public void playersCaptureOwnSeeds() {
		
		for(int i: board.getPlayer1()) {
			
			player1.setScore(player1.getScore() + i);
		}
		
		for(int i: board.getPlayer2())
			player2.setScore(player2.getScore() + i);
		
		board.clear();
		
	}

	public ArrayList<Integer> getPlayer1() {
        return board.getPlayer1();
    }

    public ArrayList<Integer> getPlayer2() {
        return board.getPlayer2();
    }

}
