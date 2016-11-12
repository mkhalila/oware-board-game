package view;

import controller.TwoPlayerController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class OwareUI extends Application {
    private Stage primaryStage;
    private MainMenu mainMenu;
    private GameBoard gameBoard;
    private BorderPane bpGameBoard;
    private HBox hbPOneHouseHolder;
    private HBox hbPTwoHouseHolder;
    private Pane board;
    private TwoPlayerController twoPController;
    private ScoreArc saScoreOne, saScoreTwo;
    private ArrayList<Button> alstAllHouses;

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
                //gameBoard = new GameBoard(830, 500, twoPController);
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

        primaryStage.setScene(mainMenu);

    }

    private void createBoard() {
        gameBoard = new GameBoard(830, 500, twoPController);
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
        hbPOneHouseHolder.getChildren().removeAll();
        hbPTwoHouseHolder.getChildren().removeAll();
        hbPOneHouseHolder.getChildren().add(new Label("Game over"));

        if (winner == 1)
            hbPTwoHouseHolder.getChildren().add(new Label("Player 1 Wins!"));
        else if (winner == 2)
            hbPTwoHouseHolder.getChildren().add(new Label("Player 2 Wins!"));
        else
            hbPTwoHouseHolder.getChildren().add(new Label("Draw!"));

    }

}