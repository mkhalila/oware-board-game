package model;

/**
 * Created by k1502469 on 08/11/16.
 */
public class Board {
    private ArrayList<Integer> player1;


    private ArrayList<Integer> player2;


    public Board() {
       player1 = new ArrayList<Integer> (6);
       player2 = new ArrayList<Integer> (6);

       for(int i = 0; i < player1.size(); ++i) {
            player1[i] = 4;
            player2[i] = 4;
       }
    }

    public ArrayList<Integer> getPlayer1() {
        return player1;
    }

    public ArrayList<Integer> getPlayer2() {
        return player2;
    }

}
