package view;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * Created by mkhal on 12/11/2016.
 */
public class SidebarButton extends Button {
    private final String sSideBarButtonStyle = "-fx-font-size: 14px; " +
            "-fx-background-color: #1b2c47; " +
            "-fx-text-fill: white;";

    public SidebarButton(String text) {
        super(text);

        setPrefWidth(114);
        setStyle(sSideBarButtonStyle);

        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setStyle(sSideBarButtonStyle + "-fx-background-color: #1692cc");
            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setStyle(sSideBarButtonStyle);
            }
        });
    }
}

