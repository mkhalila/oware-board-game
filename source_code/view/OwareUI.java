package view;

import controller.TwoPlayerController;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class OwareUI extends Application {
    private Stage primaryStage;
    private MainMenu mainMenu;
    private GameBoard gameBoard;
    private TwoPlayerController twoPController;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Oware");

        twoPController = new TwoPlayerController(this);

        createBoard();

        createMainMenu(primaryStage);

        primaryStage.show();
    }

    private void createMainMenu(Stage primaryStage) {
        mainMenu = new MainMenu(830, 500);

        Button btnTwoPlayer = mainMenu.getBtnTwoPlayer();
        btnTwoPlayer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setScene(gameBoard);
                primaryStage.setTitle("Oware: " + btnTwoPlayer.getText());
            }
        });
        mainMenu.getBtnTwoPlayer().setOnAction(twoPController);

        Button btnRandom = mainMenu.getBtnRandom();
        btnRandom.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setScene(gameBoard);
                primaryStage.setTitle("Oware: " + btnRandom.getText());
            }
        });
        mainMenu.getBtnRandom().setOnAction(twoPController);

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
        gameBoard = new GameBoard(830, 500, twoPController);

        gameBoard.getSidebar().getBtnTwoPlayer().setOnAction(twoPController);
        gameBoard.getSidebar().getBtnTwoPlayer().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setScene(gameBoard);
                primaryStage.setTitle("Oware: " + gameBoard.getSidebar().getBtnTwoPlayer().getText());
            }
        });

        gameBoard.getSidebar().getBtnRandom().setOnAction(twoPController);
        gameBoard.getSidebar().getBtnRandom().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setScene(gameBoard);
                primaryStage.setTitle("Oware: " + gameBoard.getSidebar().getBtnRandom().getText());
            }
        });

        gameBoard.getSidebar().getBtnAI().setOnAction(twoPController);
        gameBoard.getSidebar().getBtnAI().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setScene(gameBoard);
                primaryStage.setTitle("Oware: " + gameBoard.getSidebar().getBtnAI().getText());
            }
        });
    }

    public void enableP1House(int houseIn) {
        gameBoard.enableP1House(houseIn);
    }

    public void enableP2House(int houseIn) {
        gameBoard.enableP2House(houseIn);
    }

    public void updateHouseP1(int houseID, int value) {
        gameBoard.updateHouseP1(houseID, value);
    }

    public void updateHouseP2(int houseID, int value) {
        gameBoard.updateHouseP2(houseID, value);
    }

    public void updateScores(int scoreP1, int scoreP2) {
        gameBoard.updateScores(scoreP1, scoreP2);
    }

    public void disableAllP1() {
        gameBoard.disableAllP1();
    }

    public void disableAllP2() {
        gameBoard.disableAllP2();
    }

    public void gameOver(int winner) {
        primaryStage.setScene(new GameOverScene(winner, 830, 500));
        primaryStage.setTitle("Oware: GAME OVER");

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
}