package view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.stage.Stage;

public class OwareUI extends Application {
    Scene mainMenu;
    Stage primaryStage;
    BorderPane bpGameBoard;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Oware");

        mainMenu = new Scene(createMainMenu(primaryStage), 813, 500);
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

        Button btnTwoPlayer = createMenuButton("2 Player");
        vbMenuButtons.getChildren().add(btnTwoPlayer);

        Button btnRandom = createMenuButton("Random Player");
        vbMenuButtons.getChildren().add(btnRandom);

        Button btnAI = createMenuButton("AI Player");
        vbMenuButtons.getChildren().add(btnAI);

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

        HBox hbPOneHouseHolder = new HBox(10);
        hbPOneHouseHolder.setAlignment(Pos.CENTER);

        HBox hbPTwoHouseHolder = new HBox(10);
        hbPTwoHouseHolder.setAlignment(Pos.CENTER);

        vbHouseHolder.getChildren().add(hbPOneHouseHolder);
        vbHouseHolder.getChildren().add(hbPTwoHouseHolder);

        for (int i = 0; i < 6; ++i) {
            Button btnOneHouse = createHouseButton("" + i);
            hbPOneHouseHolder.getChildren().add(btnOneHouse);

            Button btnTwoHouse = createHouseButton("" + i);
            hbPTwoHouseHolder.getChildren().add(btnTwoHouse);
        }

        bpGameBoard.setCenter(vbHouseHolder);

        Arc arcOneScore = createScoreArc(0);

        HBox hbArcOne = new HBox(arcOneScore);
        hbArcOne.setAlignment(Pos.CENTER);
        bpGameBoard.setBottom(hbArcOne);

        Arc arcTwoScore = createScoreArc(180);

        HBox hbArcTwo = new HBox(arcTwoScore);
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
                vbSidebar.getChildren().add(btnCloseSB);

                Button btnTwoPlayer = createSidebarButtons("2 Player");
                vbSidebar.getChildren().add(btnTwoPlayer);

                Button btnRandom = createSidebarButtons("Random Player");
                vbSidebar.getChildren().add(btnRandom);

                Button btnAI = createSidebarButtons("AI Player");
                vbSidebar.getChildren().add(btnAI);

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
                        primaryStage.setScene(new Scene(createBoard(), 813, 500));
                        primaryStage.setTitle("Oware: Two Player");
                    }
                });

                btnRandom.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.setScene(new Scene(createBoard(), 813, 500));
                        primaryStage.setTitle("Oware: Random Player");
                    }
                });

                btnAI.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.setScene(new Scene(createBoard(), 813, 500));
                        primaryStage.setTitle("Oware: AI Player");
                    }
                });
            }
        });

        Button btnEvenSpace = createMenuButton("Menu");
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
                primaryStage.setScene(new Scene(createBoard(), 813, 500));
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
        final String sBtnStyle = "-fx-font-size: 18px; " +
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

    private Arc createScoreArc(double angleIn) {
        Arc arc = new Arc(0, 0, 200, 100, angleIn, 180.0f);
        arc.setFill(Color.valueOf("#1b2c47"));
        arc.setStroke(Color.WHITE);

        return arc;
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

}