package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 * Created by mkhal on 13/11/2016.
 */
public class Sidebar extends VBox {
    private SidebarButton btnCloseSB, btnTwoPlayer, btnRandom, btnAI;

    public Sidebar(int spacing) {
        super(5);

        setFillWidth(true);
        setStyle("-fx-padding: 5px; -fx-background-color: white");

        btnCloseSB = new SidebarButton("Close");
        btnTwoPlayer = new SidebarButton("2 Player");
        btnRandom = new SidebarButton("Random Player");
        btnAI = new SidebarButton("AI Player");

        getChildren().addAll(btnCloseSB, btnTwoPlayer, btnRandom, btnAI);
    }

    public SidebarButton getBtnCloseSB() {
        return btnCloseSB;
    }

    public SidebarButton getBtnTwoPlayer() {
        return btnTwoPlayer;
    }

    public SidebarButton getBtnRandom() {
        return btnRandom;
    }

    public SidebarButton getBtnAI() {
        return btnAI;
    }
}
