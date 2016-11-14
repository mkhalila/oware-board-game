package view;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * Represents the style of button used for the Game's sidebar menu
 * Has custom styling and hover animations.
 */
public class SidebarButton extends Button {
    //String storing the CSS styling for the button
    private final String sSideBarButtonStyle = "-fx-font-size: 14px; " +
            "-fx-background-color: #1b2c47; " +
            "-fx-text-fill: white;";

    /**
     * The constructor creates the button and defines the style and hover animations
     * @param text The text that the button will initally hold
     */
    public SidebarButton(String text) {
        super(text);

        setPrefWidth(114);
        setStyle(sSideBarButtonStyle);

        //Makes the button a lighter shade of blue on mouse hover
        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setStyle(sSideBarButtonStyle + "-fx-background-color: #1692cc");
            }
        });

        //Returns the button styling to it's initial form when mouse is removed
        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setStyle(sSideBarButtonStyle);
            }
        });
    }
}

