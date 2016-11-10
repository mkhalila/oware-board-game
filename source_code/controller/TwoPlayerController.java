package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import model.Game;
import model.Player;
import view.OwareUI;

/**
 * Created by mkhal on 10/11/2016.
 */
public class TwoPlayerController implements EventHandler{
    private Game game;
    private OwareUI view;

    @Override
    public void handle(Event event) {
        Button source = (Button) event.getSource();

        Player p1 = new Player();
        Player p2 = new Player();
        game = new Game(p1, p2);

        for (int i : game.validHouses()) {
            System.out.println(i);
        }
    }
}
