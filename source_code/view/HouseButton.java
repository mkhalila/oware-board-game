package view;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * Represents the style of button used for the Houses on the board.
 * Has custom styling and hover animations.
 */
public class HouseButton extends Button {
    //String storing the CSS styling for the button
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

    /**
     * Creates a House with custom styling and hover animations
     * @param text The initial text that appears on the button
     */
    public HouseButton(String text) {
        super(text);

        //Set style of house button
        setStyle(sBtnStyle);

        //Set the border colour to a light blue from white on mouse hover
        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setStyle(sBtnStyle + "-fx-border-color: #1692cc;");
            }
        });

        //Return the styling to initial styling when hover is exited
        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setStyle(sBtnStyle);
            }
        });
    }
}
