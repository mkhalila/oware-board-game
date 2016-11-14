package view;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * Type of Scene that represents the Game Over scene that is shown at end game
 */
public class GameOverScene extends Scene {
    //Label storing the title of the scene
    private Label lblGameOver;
    //Label storing the winning player of the game
    private Label lblWinner;

    /**
     * Creates game over scene containing Game Over title and winning player label
     * @param winner The Winner of the game (See model)
     * @param width The width of the scene
     * @param height The height of the scene
     */
    public GameOverScene(int winner, double width, double height) {
        super(new BorderPane(), width, height);

        //Sets the background colour of the scene
        BorderPane root = (BorderPane) getRoot();
        root.setStyle("-fx-background-color: #1b2c47");

        //Creates game over title with custom styling
        lblGameOver = new Label("Game Over");
        lblGameOver.setStyle("-fx-font: 100px Monospaced;" +
                " -fx-text-fill: white;");

        //Adds title to top of scene
        HBox hbGameOver = new HBox(lblGameOver);
        hbGameOver.setAlignment(Pos.CENTER);
        root.setTop(hbGameOver);

        //Sets the label of the winner according to the given winner
        if (winner != 0)
            lblWinner = new Label("Player " + winner + " Wins");
        else lblWinner = new Label("DRAW");

        //Sets style of label
        lblWinner.setStyle("-fx-font: 50px Monospaced;" +
                " -fx-text-fill: white;");

        //Adds winner label to centre of scene
        HBox hbWinner = new HBox(lblWinner);
        hbWinner.setAlignment(Pos.CENTER);
        root.setCenter(hbWinner);
    }
}
