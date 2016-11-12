package view;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * Created by mkhal on 12/11/2016.
 */
public class HouseButton extends Button {
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

    public HouseButton(String text) {
        super(text);

        setStyle(sBtnStyle);

        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setStyle(sBtnStyle + "-fx-border-color: #1692cc;");
            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setStyle(sBtnStyle);
            }
        });
    }
}
