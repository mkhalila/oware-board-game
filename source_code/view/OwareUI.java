package view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Created by mkhal on 06/11/2016.
 */
public class OwareUI extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Oware");

        Scene mainMenu = new Scene(createMainMenu(), 400, 300);
        primaryStage.setScene(mainMenu);
        
        primaryStage.show();
    }

    private Pane createMainMenu() {
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
        buttonThemeListener(btnTwoPlayer);

        Button btnRandom = new Button("Random Player");
        vbMenuButtons.getChildren().add(btnRandom);
        btnRandom.setStyle(sBtnStyle);
        buttonThemeListener(btnRandom);

        Button btnAI = new Button("AI Player");
        vbMenuButtons.getChildren().add(btnAI);
        btnAI.setStyle(sBtnStyle);
        buttonThemeListener(btnAI);

        root.setCenter(vbMenuButtons);
        return root;
    }

    private void buttonThemeListener(Button button) {
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
}
