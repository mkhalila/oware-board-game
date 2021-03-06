package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import model.*;
import view.GameBoard;
import view.OwareUI;


/**
 * Event handler and controller.
 * Reacts to button mouse clicks, controls the flow of the game and updates the view
 * 
 */
public class TwoPlayerController implements EventHandler {
    private Game game;
    private OwareUI view;
    private Player p1, p2;

    /**
     * Public constructor for TwoPlayerController
     * @param viewIn reference to view
     */
    public TwoPlayerController(OwareUI viewIn) {
        view = viewIn;
    }

    @Override
    public void handle(Event event) {
        Button source = (Button) event.getSource();

        
        // When "2 player" button is pressed
        if (source.getText().equals("2 Player")) {
            p1 = new HumanPlayer();
            p2 = new HumanPlayer();
            game = new Game(p1, p2);
            validateHouses();
            updateHouses();
            view.updateScores(p1.getScore(), p2.getScore());
        }

        // When "Random Player" button is pressed
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

        // When "AI Player" button is pressed
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

        // If the button pressed is a houseButton
        if (isHouseButton) {
            if (!game.checkScores()) {
	                validateHouses();
	
	                if (game.isPlayer1Turn()) {
	                    game.makeMove(Integer.parseInt(source.getId()));
	                    //view.moveAnimation(Integer.parseInt(source.getId()));
	                    validateHouses();
	                    updateHouses();
	                    view.updateScores(p1.getScore(), p2.getScore());
	                    if(game.validHouses().isEmpty()) {
            				game.playersCaptureOwnSeeds();
            				view.gameOver(game.returnWinner());         				
            			}
	                    if (!(p2 instanceof HumanPlayer)) {
	                    	
		                        if(!game.checkScores())
		                            game.makeMove(p2.nextMove(game.validHouses()));
		                        	//view.moveAnimation(Integer.parseInt(source.getId()));
		                        if(game.checkScores()){
				                	view.gameOver(game.returnWinner());
				                }

	                    	if(game.validHouses().isEmpty()) {
	            				game.playersCaptureOwnSeeds();
	            				view.gameOver(game.returnWinner());          				
	            			}
	                    }
	
	                } else {
	                    game.makeMove(Integer.parseInt(source.getId()));
	                    view.updateScores(p1.getScore(), p2.getScore());
	                    validateHouses();
		                updateHouses();
		                if(game.checkScores()){
		                	view.gameOver(game.returnWinner());
		                }
	                    if(game.validHouses().isEmpty()) {
            				game.playersCaptureOwnSeeds();
            				view.gameOver(game.returnWinner());          				
            			}
	                }
	
	                validateHouses();
	                updateHouses();
	                view.updateScores(p1.getScore(), p2.getScore());

                view.doAnimation();
            }else{
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
