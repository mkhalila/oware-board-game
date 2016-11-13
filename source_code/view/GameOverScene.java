package view;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * Created by mkhal on 13/11/2016.
 */
public class GameOverScene extends Scene {
    private Label lblGameOver;
    private Label lblWinner;

    public GameOverScene(int winner, double width, double height) {
        super(new BorderPane(), width, height);

        BorderPane root = (BorderPane) getRoot();
        root.setStyle("-fx-background-color: #1b2c47");

        lblGameOver = new Label("Game Over");
        lblGameOver.setStyle("-fx-font: 100px Monospaced;" +
                " -fx-text-fill: white;");

        HBox hbGameOver = new HBox(lblGameOver);
        hbGameOver.setAlignment(Pos.CENTER);
        root.setTop(hbGameOver);

        if (winner != 0)
            lblWinner = new Label("Player " + winner + " Wins");
        else lblWinner = new Label("DRAW");

        lblWinner.setStyle("-fx-font: 50px Monospaced;" +
                " -fx-text-fill: white;");

        HBox hbWinner = new HBox(lblWinner);
        hbWinner.setAlignment(Pos.CENTER);
        root.setCenter(hbWinner);
    }
}
