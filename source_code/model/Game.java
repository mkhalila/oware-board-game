package model;
import java.lang.Math;

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
            while ((house / 6 != (nextHouse) / 6) & (board.getHouseSeeds(nextHouse) == 2 || board.getHouseSeeds(nextHouse) == 3) ) {

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
