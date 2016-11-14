package view;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;

/**
 * Created by mkhal on 12/11/2016.
 */
public class ScoreArc extends StackPane {
    private Arc arc;
    private Label lblScore;

    public ScoreArc(float angleIn, String labelText) {
        super();

        arc = new Arc(0, 0, 200, 100, angleIn, 180.0f);
        arc.setFill(Color.valueOf("#1b2c47"));
        arc.setStroke(Color.WHITE);

        lblScore = new Label(labelText);
        lblScore.setStyle("-fx-text-fill: white; -fx-font-size: 20px");

        getChildren().add(arc);
        getChildren().add(lblScore);
    }

    public void updateScore(int scoreIn) {
        lblScore.setText("" + scoreIn);
    }
}
