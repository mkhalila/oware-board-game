package model;

import java.util.ArrayList;

public abstract class Player {

    private int score;

    public Player () {
         score = 0;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public abstract int nextMove(ArrayList<Integer> validHouses);

    public void increaseScore(int houseSeeds) {
        score += houseSeeds;
    }

}

