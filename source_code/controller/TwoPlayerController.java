package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import model.*;
import view.OwareUI;

public class TwoPlayerController implements EventHandler {
    private Game game;
    private OwareUI view;
    private Player p1, p2;

    public TwoPlayerController(OwareUI viewIn) {
        view = viewIn;
    }

    @Override
    public void handle(Event event) {
        Button source = (Button) event.getSource();

        if (source.getText().equals("2 Player")) {
            p1 = new HumanPlayer();
            p2 = new HumanPlayer();
            game = new Game(p1, p2);

            validateHouses();

            updateHouses();
            view.updateScores(p1.getScore(), p2.getScore());
        }

        if (source.getText().equals("Random Player")) {
            p1 = new HumanPlayer();
            p2 = new RandomPlayer();
            game = new Game(p1, p2);

            if (!game.isPlayer1Turn())  {
                game.makeMove(p2.nextMove(game.validHouses()));
            }

            validateHouses();
            updateHouses();
            view.updateScores(p1.getScore(), p2.getScore());
        }

        if (source.getText().equals("AI Player")) {
            p1 = new HumanPlayer();
            p2 = new AIPlayer();
            game = new Game(p1, p2);

            if (!game.isPlayer1Turn()) {
                game.makeMove(p2.nextMove(game.validHouses()));
            }

            validateHouses();
            updateHouses();
            view.updateScores(p1.getScore(), p2.getScore());
        }

        boolean isHouseButton = false;
        try {
            Integer.parseInt(source.getId());
            isHouseButton = true;
        } catch (NumberFormatException e) {}

        if (isHouseButton) {
            if (!game.checkScores()) {
                validateHouses();

                if (game.isPlayer1Turn()) {
                    game.makeMove(Integer.parseInt(source.getId()));
                    validateHouses();
                    updateHouses();
                    view.updateScores(p1.getScore(), p2.getScore());
                    game.print();
                    if (!(p2 instanceof HumanPlayer)) {
                        game.print();
                        if(!game.checkScores())
                            game.makeMove(p2.nextMove(game.validHouses()));
                    }

                } else {
                    game.makeMove(Integer.parseInt(source.getId()));
                }

                validateHouses();
                updateHouses();
                view.updateScores(p1.getScore(), p2.getScore());
                game.print();
            }


            if (game.checkScores()) {
                view.gameOver(game.returnWinner());
            }
        }
    }


    private void updateHouses() {
        for (int i = 0; i < game.getPlayer1Houses().size(); ++i) {
            view.updateHouseP1(i, game.getPlayer1Houses().get(i));
        }

        for (int i = 6; i < 12; ++i) {
            view.updateHouseP2(i, game.getPlayer2Houses().get(i-6));
        }
    }

    private void validateHouses() {
        if (game.isPlayer1Turn()) {
            for (int i : game.validHouses()) {
                view.enableP1House(i);
                view.disableAllP2();
            }
        } else {
            for (int i : game.validHouses()) {
                view.enableP2House(i);
                view.disableAllP1();
            }
        }
    }
}
