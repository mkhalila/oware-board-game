package model;
import java.lang.Math;

public class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private boolean isFinished;
    private boolean isP1Turn;

    public Game() {
        board = new Board();
        player1 = new Player();
        player2 = new Player();
        isFinished = false;
        isP1Turn = Math.random() < 0.5;
    }

    
}
