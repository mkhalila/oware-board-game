package model;
import java.lang.Math;

public class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private boolean isFinished;
    private boolean isP1Turn;

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

            board.incrementHouse(++nextHouse % 12);
            --seeds;
        }

        while((board.getHouseSeeds(nextHouse) == 2 || board.getHouseSeeds(nextHouse) == 3) && (house / 6 != nextHouse / 6)) {

            if(isP1Turn) {
                player1.increaseScore(board.getHouseSeeds(nextHouse));
            } else {
                player2.increaseScore(board.getHouseSeeds(nextHouse));
            }

            board.emptyHouse(nextHouse);
            --nextHouse;
        }

        isP1Turn = !isP1Turn;

    }

}
