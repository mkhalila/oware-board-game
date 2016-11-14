package view;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * Represents the style of button used for the Game's menu buttons
 * Has custom styling and hover animations.
 */
public class MenuButton extends Button {
    private final String sBtnStyle = "-fx-font-size: 18px; " +
            "-fx-background-color: #1b2c47; " +
            "-fx-border-color: white;" +
            " -fx-border-radius: 100%; " +
            "-fx-text-fill: white;";

    public MenuButton(String text) {
        super(text);

        setPrefWidth(200);
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
