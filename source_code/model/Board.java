package model;
import java.util.ArrayList;


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
    
    public int getHouseSeeds (int house) {
        if(house < 6) {
            return player1.get(house);
        } else {
            return player2.get(house - 6);
        }
    }

    public boolean canCapture(int nextHouse) {
        if(nextHouse < 6) {
            for(int i = 0; i <= nextHouse; ++i) {
                if(player1.get(i) != 2 || player1.get(i) != 3) {
                    return true;
                }
            }

            for(int i = nextHouse + 1; i < 6; ++i) {
                if(player1.get(i) > 0) {
                    return true;
                }
            }
        } else {
            for(int i = 0; i <= nextHouse - 6; ++i) {
                if(player2.get(i) != 2 || player2.get(i) != 3) {
                    return true;
                }
            }

            for(int i = nextHouse - 5; i < 6; ++i) {
                if(player2.get(i) > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public void emptyHouse (int house) {
        if(house < 6) {
            player1.set(house, 0);
        } else {
            player2.set(house - 6, 0);
        }
    }

    public void incrementHouse (int house) {
        if(house < 6) {
            player1.set(house, player1.get(house) + 1);
        } else {
            player2.set(house-6, player2.get(house-6) + 1);
        }
    }


}
