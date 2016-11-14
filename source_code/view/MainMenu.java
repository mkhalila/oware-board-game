package view;

import javafx.beans.NamedArg;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Type of Scene that represents the Main Menu of the game with a title and 3 game mode buttons
 */
public class MainMenu extends Scene {
    //Label containing title of the game
    private Label lblMenuTitle;
    //Holder of the label title
    private HBox hbTitlePane;
    //Vertical box for holding menu buttons
    private VBox vbMenuButtons;
    //Menu Button for two player mode
    private MenuButton btnTwoPlayer;
    //Menu button for random player mode
    private MenuButton btnRandom;
    //Menu button for AI player mode
    private MenuButton btnAI;

    /**
     * Creates Main Menu scene containing title and menu buttons
     * @param width Initial width of the scene
     * @param height Initial height of the scene
     */
    public MainMenu(double width, double height) {
        //Creates a Scene with a BorderPane as root container
        super(new BorderPane(), width, height);

        BorderPane root = (BorderPane) getRoot();

        //Sets BG colour of scene
        root.setStyle("-fx-background-color: #1b2c47");

        //Creates styled title
        lblMenuTitle = new Label("Oware");
        lblMenuTitle.setStyle("-fx-font: 100px Monospaced;" +
                " -fx-text-fill: white;");

        //Adds label title to container
        hbTitlePane = new HBox(lblMenuTitle);
        hbTitlePane.setAlignment(Pos.CENTER);

        //Add title to top of scene
        root.setTop(hbTitlePane);
        root.setAlignment(hbTitlePane, Pos.CENTER);

        //Creates container for Menu Buttons
        vbMenuButtons = new VBox(10);
        vbMenuButtons.setAlignment(Pos.CENTER);

        //Creates 3 menu buttons
        btnTwoPlayer = new MenuButton("2 Player");
        btnRandom = new MenuButton("Random Player");
        btnAI = new MenuButton("AI Player");

        //Adds buttons to container
        vbMenuButtons.getChildren().addAll(btnTwoPlayer, btnRandom, btnAI);

        //Adds button container to scene
        root.setCenter(vbMenuButtons);
    }

    /**
     * Allows access to two player button
     * @return The Menu two player button
     */
    public Button getBtnTwoPlayer() {
        return btnTwoPlayer;
    }

    /**
     * Allows access to random player button
     * @return The Menu random player button
     */
    public Button getBtnRandom() {
        return btnRandom;
    }

    /**
     * Allows access to AI player button
     * @return The Menu AI player button
     */
    public Button getBtnAI() { return btnAI; }
}
