package view;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * Represents the style of button used for the Game's menu buttons
 * Has custom styling and hover animations.
 */
public class MenuButton extends Button {
    //String storing the CSS styling for the button
    private final String sBtnStyle = "-fx-font-size: 18px; " +
            "-fx-background-color: #1b2c47; " +
            "-fx-border-color: white;" +
            " -fx-border-radius: 100%; " +
            "-fx-text-fill: white;";

    /**
     * Creates menu button with custom styling and hover animations
     * @param text The initial text that will be displayed on the button
     */
    public MenuButton(String text) {
        super(text);

        //Set style of button
        setPrefWidth(200);
        setStyle(sBtnStyle);

        //Set the bg colour to a light blue from white on mouse hover
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
