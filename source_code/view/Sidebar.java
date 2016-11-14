package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 * Represents the whole sidebar used in the game board's menu.
 * Extends VBox and contains SidebarButton objects.
 */
public class Sidebar extends VBox {
    //Fields which store the SidebarButtons that are held in the Sidebar
    private SidebarButton btnCloseSB, btnTwoPlayer, btnRandom, btnAI;

    /**
     * Creates a Sidebar containing 3 Sidebar buttons and a button to close the Sidebar
     * @param spacing Defines the vertical spacing between nodes of the Sidebar
     */
    public Sidebar(int spacing) {
        super(5);

        //Side bar will fill all the vertical space of the container
        setFillWidth(true);
        setStyle("-fx-padding: 5px; -fx-background-color: white");

        //Creates Buttons held in the Sidebar
        btnCloseSB = new SidebarButton("Close");
        btnTwoPlayer = new SidebarButton("2 Player");
        btnRandom = new SidebarButton("Random Player");
        btnAI = new SidebarButton("AI Player");

        //Adds the buttons to the sidebar
        getChildren().addAll(btnCloseSB, btnTwoPlayer, btnRandom, btnAI);
    }

    /**
     * Allows access to the Close button
     * @return The sidebar close button
     */
    public SidebarButton getBtnCloseSB() {
        return btnCloseSB;
    }

    /**
     * Allows access to the Two Player button
     * @return The sidebar Two Player button
     */
    public SidebarButton getBtnTwoPlayer() {
        return btnTwoPlayer;
    }

    /**
     * Allows access to the Random button
     * @return The sidebar Random button
     */
    public SidebarButton getBtnRandom() {
        return btnRandom;
    }

    /**
     * Allows access to the AI Button
     * @return The sidebar AI button
     */
    public SidebarButton getBtnAI() {
        return btnAI;
    }
}
