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

    public TwoPlayerController(OwareUI viewIn) {
        view = viewIn;
    }

    @Override
    public void handle(Event event) {
        Button source = (Button) event.getSource();

        if (source.getText().equals("2 Player")) {
            Player p1 = new Player();
            Player p2 = new Player();
            game = new Game(p1, p2);

            for (int i : game.validHouses()) {
                view.enableHouse(i);
            }
        }

        boolean isHouseButton = false;
        try {
            Integer.parseInt(source.getId());
            isHouseButton = true;
        } catch (NumberFormatException e) {}

        if (isHouseButton) {
            if(!game.checkScores()) {
                game.makeMove(Integer.parseInt(source.getId()));
                game.print();
            }
        }
    }
}
