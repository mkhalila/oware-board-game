package view;

import controller.TwoPlayerController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.stage.Stage;

import java.util.ArrayList;

public class OwareUI extends Application {
    private Scene mainMenu;
    private Stage primaryStage;
    private BorderPane bpGameBoard;
    private HBox hbPOneHouseHolder;
    private HBox hbPTwoHouseHolder;
    private Button btnTwoPlayer;
    private Pane board;
    private TwoPlayerController twoPController;
    private Label lblP1Score, lblP2Score;
    private ArrayList<Button> alstAllHouses;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Oware");

        twoPController = new TwoPlayerController(this);

        mainMenu = new Scene(createMainMenu(primaryStage), 830, 500);

        board = createBoard();
        primaryStage.setScene(mainMenu);

        primaryStage.show();
    }

    private Pane createMainMenu(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #1b2c47");

        Label lblMenuTitle = new Label("Oware");
        lblMenuTitle.setStyle("-fx-font: 100px Monospaced;" +
                " -fx-text-fill: white;");

        HBox hbTitlePane = new HBox(lblMenuTitle);
        hbTitlePane.setAlignment(Pos.CENTER);

        root.setTop(hbTitlePane);
        root.setAlignment(hbTitlePane, Pos.CENTER);

        VBox vbMenuButtons = new VBox(10);
        vbMenuButtons.setAlignment(Pos.CENTER);

        btnTwoPlayer = createMenuButton("2 Player");
        Button btnRandom = createMenuButton("Random Player");
        Button btnAI = createMenuButton("AI Player");

        btnTwoPlayer.setOnAction(twoPController);
        btnRandom.setOnAction(twoPController);

        vbMenuButtons.getChildren().addAll(btnTwoPlayer, btnRandom, btnAI);

        root.setCenter(vbMenuButtons);
        return root;
    }

    private Pane createBoard() {
        BorderPane root = new BorderPane();
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
            Button btnOneHouse = createHouseButton("");
            btnOneHouse.setId("" + i);
            btnOneHouse.setDisable(true);
            hbPOneHouseHolder.getChildren().add(btnOneHouse);
            alstAllHouses.add(btnOneHouse);
        }

        for (int i =11; i > 5; --i) {
            Button btnTwoHouse = createHouseButton("");
            btnTwoHouse.setId("" + i);
            btnTwoHouse.setDisable(true);
            hbPTwoHouseHolder.getChildren().add(btnTwoHouse);
            alstAllHouses.add(6, btnTwoHouse);
        }

        bpGameBoard.setCenter(vbHouseHolder);

        lblP1Score = new Label("0");
        StackPane spScoreOne = createScoreArc(0, lblP1Score);

        HBox hbArcOne = new HBox(spScoreOne);
        hbArcOne.setAlignment(Pos.CENTER);
        bpGameBoard.setBottom(hbArcOne);

        lblP2Score = new Label("0");
        StackPane spScoreTwo = createScoreArc(180, lblP2Score);

        HBox hbArcTwo = new HBox(spScoreTwo);
        hbArcTwo.setAlignment(Pos.CENTER);
        bpGameBoard.setTop(hbArcTwo);

        Button btnToMenu = createMenuButton("Menu");
        btnToMenu.setPrefWidth(80);

        BorderPane bpToMenu = new BorderPane();

        HBox hbToMenu = new HBox(btnToMenu);
        bpToMenu.setCenter(hbToMenu);
        hbToMenu.setStyle("-fx-padding: 5px");
        root.setLeft(bpToMenu);

        btnToMenu.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                VBox vbSidebar = new VBox(5);
                vbSidebar.setFillWidth(true);
                vbSidebar.setStyle("-fx-padding: 5px; -fx-background-color: white");

                Button btnCloseSB = createSidebarButtons("Close");
                Button btnTwoPlayer = createSidebarButtons("2 Player");
                Button btnRandom = createSidebarButtons("Random Player");
                Button btnAI = createSidebarButtons("AI Player");

                vbSidebar.getChildren().addAll(btnCloseSB, btnTwoPlayer, btnRandom, btnAI);

                bpToMenu.setLeft(vbSidebar);

                hbToMenu.getChildren().remove(btnToMenu);

                btnCloseSB.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        bpToMenu.getChildren().remove(vbSidebar);
                        hbToMenu.getChildren().add(btnToMenu);
                    }
                });

                btnTwoPlayer.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.setScene(new Scene(createBoard(), 830, 500));
                        primaryStage.setTitle("Oware: Two Player");
                    }
                });

                btnRandom.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.setScene(new Scene(createBoard(), 830, 500));
                        primaryStage.setTitle("Oware: Random Player");
                    }
                });

                btnAI.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.setScene(new Scene(createBoard(), 830, 500));
                        primaryStage.setTitle("Oware: AI Player");
                    }
                });
            }
        });

        Button btnEvenSpace = createMenuButton("Menu");
        btnEvenSpace.setPrefWidth(80);
        btnEvenSpace.setVisible(false);

        HBox hbEvenSpace = new HBox(btnEvenSpace);
        hbEvenSpace.setStyle("-fx-padding: 5px");
        root.setRight(hbEvenSpace);

        return root;
    }

    private Button createMenuButton(String text) {
        final String sBtnStyle = "-fx-font-size: 18px; " +
                "-fx-background-color: #1b2c47; " +
                "-fx-border-color: white;" +
                " -fx-border-radius: 100%; " +
                "-fx-text-fill: white;";

        Button button = new Button(text);
        button.setPrefWidth(200);
        button.setStyle(sBtnStyle);

        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setScene(new Scene(board, 830, 500));
                primaryStage.setTitle("Oware: " + button.getText());
            }
        });

        button.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                button.setStyle(sBtnStyle + "-fx-border-color: #1692cc;");
            }
        });

        button.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                button.setStyle(sBtnStyle);
            }
        });

        return button;
    }

    private Button createHouseButton(String title) {
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

        Button button = new Button(title);
        button.setStyle(sBtnStyle);
        button.setOnAction(twoPController);

        button.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                button.setStyle(sBtnStyle + "-fx-border-color: #1692cc;");

                Button other = alstAllHouses.get(calcHighlight(button));

                other.setStyle(other.getStyle() + "-fx-border-width: 5px;");
            }
        });

        button.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                button.setStyle(sBtnStyle);

                Button other = alstAllHouses.get(calcHighlight(button));
                other.setStyle(other.getStyle() + "-fx-border-width: 1px;");

            }
        });

        return button;
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

    private StackPane createScoreArc(double angleIn, Label lblScore) {
        Arc arc = new Arc(0, 0, 200, 100, angleIn, 180.0f);
        arc.setFill(Color.valueOf("#1b2c47"));
        arc.setStroke(Color.WHITE);

        lblScore.setStyle("-fx-text-fill: white; -fx-font-size: 20px");
        
        StackPane stackPane = new StackPane(arc);
        stackPane.getChildren().add(lblScore);

        return stackPane;
    }

    private Button createSidebarButtons(String text) {
        final String sSideBarButtonStyle = "-fx-font-size: 14px; " +
                "-fx-background-color: #1b2c47; " +
                "-fx-text-fill: white;";

        Button button = new Button(text);
        button.setPrefWidth(114);
        button.setStyle(sSideBarButtonStyle);

        button.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                button.setStyle(sSideBarButtonStyle + "-fx-background-color: #1692cc");
            }
        });

        button.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                button.setStyle(sSideBarButtonStyle);
            }
        });

        return button;
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
        lblP1Score.setText("" + scoreP1);
        lblP2Score.setText("" + scoreP2);
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