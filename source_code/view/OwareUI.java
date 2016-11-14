package view;

import controller.TwoPlayerController;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Represents the entire UI application containing a MainMenu Scene, GameBoard (+ Sidebar) scene, GameOver scene
 */
public class OwareUI extends Application {
    //Primary stage
    private Stage primaryStage;
    //Main Menu scene
    private MainMenu mainMenu;
    //Current game board scene
    private GameBoard gameBoard;
    //Reference to controller of game
    private TwoPlayerController twoPController;

    /**
     * Creates a controller, Main menu scene and initial game board scene
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Oware");

        //Initialise constructor
        twoPController = new TwoPlayerController(this);

        //Creates game board scene
        createBoard();

        //Creates main menu
        createMainMenu(primaryStage);

        primaryStage.show();

        //Window resize listeners for seed animations
        gameBoard.heightProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				gameBoard.doAnimation();
			}
		}) ;
        gameBoard.widthProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				gameBoard.doAnimation();
			}
		});
    }

    private void createMainMenu(Stage primaryStage) {
        mainMenu = new MainMenu(890, 500);

        //Adds listener for two player button
        Button btnTwoPlayer = mainMenu.getBtnTwoPlayer();
        btnTwoPlayer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setScene(gameBoard);
                primaryStage.setTitle("Oware: " + btnTwoPlayer.getText());
                gameBoard.doAnimation();
            }
        });
        mainMenu.getBtnTwoPlayer().setOnAction(twoPController);

        //Adds listener for random player button
        Button btnRandom = mainMenu.getBtnRandom();
        btnRandom.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setScene(gameBoard);
                primaryStage.setTitle("Oware: " + btnRandom.getText());
            }
        });
        mainMenu.getBtnRandom().setOnAction(twoPController);

        //Adds listener for AI player button
        Button btnAI = mainMenu.getBtnAI();
        btnAI.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setScene(gameBoard);
                primaryStage.setTitle("Oware: " + btnAI.getText());
            }
        });
        mainMenu.getBtnAI().setOnAction(twoPController);

        primaryStage.setScene(mainMenu);

    }

    private void createBoard() {
        gameBoard = new GameBoard(890, 500, twoPController);

        //Event handler for sidebar two player button
        gameBoard.getSidebar().getBtnTwoPlayer().setOnAction(twoPController);
        gameBoard.getSidebar().getBtnTwoPlayer().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setScene(gameBoard);
                primaryStage.setTitle("Oware: " + gameBoard.getSidebar().getBtnTwoPlayer().getText());
            }
        });

        //Event handler for sidebar randon player button
        gameBoard.getSidebar().getBtnRandom().setOnAction(twoPController);
        gameBoard.getSidebar().getBtnRandom().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setScene(gameBoard);
                primaryStage.setTitle("Oware: " + gameBoard.getSidebar().getBtnRandom().getText());
            }
        });

        //Event handler for sidebar AI player button
        gameBoard.getSidebar().getBtnAI().setOnAction(twoPController);
        gameBoard.getSidebar().getBtnAI().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setScene(gameBoard);
                primaryStage.setTitle("Oware: " + gameBoard.getSidebar().getBtnAI().getText());
            }
        });
    }

    /**
     * Enable the button house for a given house of player 1
     * @param houseIn The house to enable
     */
    public void enableP1House(int houseIn) {
        gameBoard.enableP1House(houseIn);
    }

    /**
     * Enable the button house for a given house of player 2
     * @param houseIn The house to enable
     */
    public void enableP2House(int houseIn) {
        gameBoard.enableP2House(houseIn);
    }

    /**
     * Update no. of seeds in a given house of player 1
     * @param houseID The house to update
     * @param value The value to update the house to
     */
    public void updateHouseP1(int houseID, int value) {
        gameBoard.updateHouseP1(houseID, value);
    }

    /**
     * Update no. of seeds in a given house of player 1
     * @param houseID The house to update
     * @param value The value to update the house to
     */
    public void updateHouseP2(int houseID, int value) {
        gameBoard.updateHouseP2(houseID, value);
    }

    /**
     * Updates scores of both players
     * @param scoreP1 Score of player 1
     * @param scoreP2 Score of player 2
     */
    public void updateScores(int scoreP1, int scoreP2) {
        gameBoard.updateScores(scoreP1, scoreP2);
    }

    /**
     * Disables all of Player 1's buttons
     */
    public void disableAllP1() {
        gameBoard.disableAllP1();
    }

    /**
     * Disables all of player 2's button
     */
    public void disableAllP2() {
        gameBoard.disableAllP2();
    }

    /**
     * Creates and displays game over scene when the game ends
     * @param winner Winner of the game
     */
    public void gameOver(int winner) {
        primaryStage.setScene(new GameOverScene(winner, 830, 500));
        primaryStage.setTitle("Oware: GAME OVER");

        //Only shows the Game over scene for 5 seconds before transitioning back to main menu
        PauseTransition gameOverDelay = new PauseTransition(Duration.seconds(5));
        gameOverDelay.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(mainMenu);
                primaryStage.setTitle("Oware");
            }
        });
        gameOverDelay.play();
    }

    /**
     * Calls animation for the seeds rotation
     */
	public void doAnimation() {
		gameBoard.doAnimation();
		
	}

    /**
     * Calls animation for sowing seeds.
     * @param houseNumber
     */
	public void moveAnimation(int houseNumber) {
		gameBoard.moveAnimation(houseNumber);
	}

}