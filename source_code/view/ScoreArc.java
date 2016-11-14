package view;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;

/**
 * Represents the Semi-circular arc used to display a Player's score
 */
public class ScoreArc extends StackPane {
    //The semi-circular Arc
    private Arc arc;
    //The label representing the player's current score
    private Label lblScore;

    /**
     * Creates a ScoreArc
     * @param angleIn Represents the angle that the Arc is drawn from
     * @param labelText Represents the text that will be initially stored in the Arc
     */
    public ScoreArc(float angleIn, String labelText) {
        super();

        //Creates arc
        arc = new Arc(0, 0, 200, 100, angleIn, 180.0f);
        //Sets background colour as background colour of scene
        arc.setFill(Color.valueOf("#1b2c47"));
        //Sets colour of outline to white
        arc.setStroke(Color.WHITE);

        //Creates label with given text
        lblScore = new Label(labelText);
        //Styles the label
        lblScore.setStyle("-fx-text-fill: white; -fx-font-size: 20px");

        //Adds arc and label to the StackPane root
        getChildren().add(arc);
        getChildren().add(lblScore);
    }

    /**
     * Used to update the score contained by the ScoreArc instance
     * @param scoreIn The integer given to set the score as
     */
    public void updateScore(int scoreIn) {
        lblScore.setText("" + scoreIn);
    }
}
