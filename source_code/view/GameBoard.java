package view;

import controller.TwoPlayerController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.ArrayList;

/**
 * Created by mkhal on 12/11/2016.
 */
public class GameBoard extends Scene {
    private BorderPane bpGameBoard;
    private HBox hbPOneHouseHolder, hbPTwoHouseHolder;
    private ArrayList<Button> alstAllHouses;
    private ScoreArc saScoreOne, saScoreTwo;
    private TwoPlayerController controller;
    private Sidebar sidebar;

    public GameBoard(double width, double height, TwoPlayerController controller) {
        super(new BorderPane(), width, height);

        this.controller = controller;

        sidebar = new Sidebar(5);

        BorderPane root = (BorderPane) getRoot();
        root.setStyle("-fx-background-color: #1b2c47");

        bpGameBoard = new BorderPane();
        root.setCenter(bpGameBoard);
        bpGameBoard.setStyle("-fx-background-color: #1b2c47");

        VBox vbHouseHolder = new VBox(20);
        vbHouseHolder.setAlignment(Pos.CENTER);

        hbPOneHouseHolder = new HBox(10);
        hbPOneHouseHolder.setAlignment(Pos.CENTER);

        hbPTwoHouseHolder = new HBox(10);
        hbPTwoHouseHolder.setAlignment(Pos.CENTER);

        vbHouseHolder.getChildren().addAll(hbPTwoHouseHolder, hbPOneHouseHolder);

        alstAllHouses = new ArrayList<>(12);

        for (int i = 0; i < 6; ++i) {
            Button btnOneHouse = new HouseButton("");
            hoverHouseButton(btnOneHouse);
            btnOneHouse.setOnAction(controller);
            btnOneHouse.setId("" + i);
            btnOneHouse.setDisable(true);
            hbPOneHouseHolder.getChildren().add(btnOneHouse);
            alstAllHouses.add(btnOneHouse);
        }

        for (int i = 11; i > 5; --i) {
            Button btnTwoHouse = new HouseButton("");
            hoverHouseButton(btnTwoHouse);
            btnTwoHouse.setOnAction(controller);
            btnTwoHouse.setId("" + i);
            btnTwoHouse.setDisable(true);
            hbPTwoHouseHolder.getChildren().add(btnTwoHouse);
            alstAllHouses.add(6, btnTwoHouse);
        }

        bpGameBoard.setCenter(vbHouseHolder);

        saScoreOne = new ScoreArc(0, "0");

        HBox hbArcOne = new HBox(saScoreOne);
        hbArcOne.setAlignment(Pos.CENTER);
        bpGameBoard.setBottom(hbArcOne);

        saScoreTwo = new ScoreArc(180, "0");

        HBox hbArcTwo = new HBox(saScoreTwo);
        hbArcTwo.setAlignment(Pos.CENTER);
        bpGameBoard.setTop(hbArcTwo);

        MenuButton btnToMenu = new MenuButton("Menu");
        btnToMenu.setPrefWidth(80);

        BorderPane bpToMenu = new BorderPane();

        HBox hbToMenu = new HBox(btnToMenu);
        bpToMenu.setCenter(hbToMenu);
        hbToMenu.setStyle("-fx-padding: 5px");
        root.setLeft(bpToMenu);

        btnToMenu.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                bpToMenu.setLeft(sidebar);
                hbToMenu.getChildren().remove(btnToMenu);

                sidebar.getBtnCloseSB().setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        bpToMenu.getChildren().remove(sidebar);
                        hbToMenu.getChildren().add(btnToMenu);
                    }
                });
            }
        });

        MenuButton btnEvenSpace = new MenuButton("Menu");
        btnEvenSpace.setPrefWidth(80);
        btnEvenSpace.setVisible(false);

        HBox hbEvenSpace = new HBox(btnEvenSpace);
        hbEvenSpace.setStyle("-fx-padding: 5px");
        root.setRight(hbEvenSpace);

    }

    private void hoverHouseButton(Button btnHouse) {
        final String sBtnStyle = "-fx-font-size: 20px; " +
                "-fx-background-color: #1b2c47; " +
                "-fx-border-color: white;" +
                " -fx-border-radius: 100%; " +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 5em; " +
                "-fx-min-width: 100px; " +
                "-fx-min-height: 100px; " +
                "-fx-max-width: 100px; " +
                "-fx-max-height: 100px;";

        btnHouse.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnHouse.setStyle(sBtnStyle + "-fx-border-color: #1692cc;");

                Button other = alstAllHouses.get(calcHighlight(btnHouse));

                other.setStyle(other.getStyle() + "-fx-border-width: 5px;");
            }
        });

        btnHouse.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnHouse.setStyle(sBtnStyle);

                Button other = alstAllHouses.get(calcHighlight(btnHouse));
                other.setStyle(other.getStyle() + "-fx-border-width: 1px;");

            }
        });

        btnHouse.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                removeHouseHighlighting();
            }
        });
    }

    private int calcHighlight(Button button) {
        int noToMove = Integer.parseInt(button.getText());

        int startingIndex = -1;
        for (Button btn : alstAllHouses) {
            if (btn.getId() == button.getId()) {
                startingIndex = alstAllHouses.indexOf(btn);
            }
        }

        int n =  noToMove/12;
        noToMove += startingIndex;
        noToMove += n;
        noToMove %= 12;

        return noToMove;
    }

    private void removeHouseHighlighting() {
        for (Button b : alstAllHouses) b.setStyle(b.getStyle() + "-fx-border-width: 1px;");
    }

    public void enableP1House(int houseIn) {
        if ((houseIn >= 0) || (houseIn < 6)) {
            for (Node b : hbPOneHouseHolder.getChildren()) {
                if (houseIn == Integer.parseInt(b.getId())) b.setDisable(false);
            }
        }
    }

    public void enableP2House(int houseIn) {
        if ((houseIn >= 0) || (houseIn < 6)) {
            for (Node b : hbPTwoHouseHolder.getChildren()) {
                if (houseIn == Integer.parseInt(b.getId())) b.setDisable(false);
            }
        }
    }

    public void updateHouseP1(int houseID, int value) {
        for (Node b : hbPOneHouseHolder.getChildren()) {
            if (Integer.parseInt(b.getId()) == houseID) {
                Button btn = (Button) b;
                btn.setText(""+ value);
            }
        }
    }

    public void updateHouseP2(int houseID, int value) {
        for (Node b : hbPTwoHouseHolder.getChildren()) {
            if (Integer.parseInt(b.getId()) == houseID) {
                Button btn = (Button) b;
                btn.setText(""+ value);
            }
        }
    }

    public void updateScores(int scoreP1, int scoreP2) {
        saScoreOne.updateScore(scoreP1);
        saScoreTwo.updateScore(scoreP2);
    }

    public void disableAllP1() {
        for (Node b : hbPOneHouseHolder.getChildren()) {
            b.setDisable(true);
        }
    }

    public void disableAllP2() {
        for (Node b : hbPTwoHouseHolder.getChildren()) {
            b.setDisable(true);
        }
    }

    public Sidebar getSidebar() {
        return sidebar;
    }

    public void gameOver(int winner) {
        System.out.println("Got here gameBoard");
        hbPOneHouseHolder.getChildren().removeAll();
        hbPTwoHouseHolder.getChildren().removeAll();
        hbPOneHouseHolder.getChildren().add(new Label("Game over"));

        System.out.println("Should've removed buttons");

        if (winner == 1) {
            hbPTwoHouseHolder.getChildren().add(new Label("Player 1 Wins!"));
            System.out.println("Should've added labels");
        }
        else if (winner == 2)
            hbPTwoHouseHolder.getChildren().add(new Label("Player 2 Wins!"));
        else
            hbPTwoHouseHolder.getChildren().add(new Label("Draw!"));

    }
}
