package view;

import javafx.application.Application;
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
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

/**
 * Created by mkhal on 06/11/2016.
 */
public class OwareUI extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Oware");

        Scene mainMenu = new Scene(createMainMenu(primaryStage), 800, 500);
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

        VBox vbMenuButtons = new VBox();
        vbMenuButtons.setAlignment(Pos.CENTER);
        vbMenuButtons.setSpacing(10);

        final String sBtnStyle = "-fx-font-size: 18px; " +
                "-fx-background-color: #1b2c47; " +
                "-fx-border-color: white;" +
                " -fx-border-radius: 100%; " +
                "-fx-text-fill: white;";

        Button btnTwoPlayer = new Button("2 Player");
        vbMenuButtons.getChildren().add(btnTwoPlayer);
        btnTwoPlayer.setStyle(sBtnStyle);
        menuButtonAnimations(btnTwoPlayer);

        btnTwoPlayer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setScene(new Scene(createBoard(), 800, 500));
            }
        });

        Button btnRandom = new Button("Random Player");
        vbMenuButtons.getChildren().add(btnRandom);
        btnRandom.setStyle(sBtnStyle);
        menuButtonAnimations(btnRandom);

        btnRandom.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setScene(new Scene(createBoard(), 800, 500));
            }
        });

        Button btnAI = new Button("AI Player");
        vbMenuButtons.getChildren().add(btnAI);
        btnAI.setStyle(sBtnStyle);
        menuButtonAnimations(btnAI);

        btnAI.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setScene(new Scene(createBoard(), 800, 500));
            }
        });

        root.setCenter(vbMenuButtons);
        return root;
    }

    private Pane createBoard() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #1b2c47");

        VBox vbHouseHolder = new VBox();
        vbHouseHolder.setAlignment(Pos.CENTER);
        vbHouseHolder.setSpacing(20);

        HBox hbPOneHouseHolder = new HBox();
        hbPOneHouseHolder.setSpacing(10);
        hbPOneHouseHolder.setAlignment(Pos.CENTER);

        HBox hbPTwoHouseHolder = new HBox();
        hbPTwoHouseHolder.setSpacing(10);
        hbPTwoHouseHolder.setAlignment(Pos.CENTER);

        vbHouseHolder.getChildren().add(hbPOneHouseHolder);
        vbHouseHolder.getChildren().add(hbPTwoHouseHolder);

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

        for (int i = 0; i < 6; ++i) {
            Button btnOneHouse = new Button("" + i);
            btnOneHouse.setStyle(sBtnStyle);
            houseButtonAnimations(btnOneHouse);
            hbPOneHouseHolder.getChildren().add(btnOneHouse);

            Button btnTwoHouse = new Button("" + i);
            btnTwoHouse.setStyle(sBtnStyle);
            houseButtonAnimations(btnTwoHouse);
            hbPTwoHouseHolder.getChildren().add(btnTwoHouse);
        }

        root.setCenter(vbHouseHolder);

        Arc arcOneScore = new Arc();
        arcOneScore.setRadiusX(200);
        arcOneScore.setRadiusY(100);
        arcOneScore.setStartAngle(0.0f);
        arcOneScore.setLength(180.0f);
        arcOneScore.setType(ArcType.ROUND);
        arcOneScore.setFill(Color.valueOf("#1b2c47"));
        arcOneScore.setStroke(Color.WHITE);

        HBox hbArcOne = new HBox(arcOneScore);
        hbArcOne.setAlignment(Pos.CENTER);
        root.setBottom(hbArcOne);

        Arc arcTwoScore = new Arc();
        arcTwoScore.setRadiusX(200);
        arcTwoScore.setRadiusY(100);
        arcTwoScore.setStartAngle(180.0f);
        arcTwoScore.setLength(180.0f);
        arcTwoScore.setType(ArcType.ROUND);
        arcTwoScore.setFill(Color.valueOf("#1b2c47"));
        arcTwoScore.setStroke(Color.WHITE);

        HBox hbArcTwo = new HBox(arcTwoScore);
        hbArcTwo.setAlignment(Pos.CENTER);
        root.setTop(hbArcTwo);

        return root;
    }

    private void menuButtonAnimations(Button button) {
        final String sBtnStyle = "-fx-font-size: 18px; " +
                "-fx-background-color: #1b2c47; " +
                "-fx-border-color: white;" +
                " -fx-border-radius: 100%; " +
                "-fx-text-fill: white;";

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
    }

    private void houseButtonAnimations(Button button) {
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
    }
}
