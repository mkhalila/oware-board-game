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
 * Created by mkhal on 12/11/2016.
 */
public class MainMenu extends Scene {
    private Label lblMenuTitle;
    private HBox hbTitlePane;
    private VBox vbMenuButtons;
    private MenuButton btnTwoPlayer;
    private MenuButton btnRandom;
    private MenuButton btnAI;

    public MainMenu(double width, double height) {
        super(new BorderPane(), width, height);

        BorderPane root = (BorderPane) getRoot();

        root.setStyle("-fx-background-color: #1b2c47");

        lblMenuTitle = new Label("Oware");
        lblMenuTitle.setStyle("-fx-font: 100px Monospaced;" +
                " -fx-text-fill: white;");

        hbTitlePane = new HBox(lblMenuTitle);
        hbTitlePane.setAlignment(Pos.CENTER);

        root.setTop(hbTitlePane);
        root.setAlignment(hbTitlePane, Pos.CENTER);

        vbMenuButtons = new VBox(10);
        vbMenuButtons.setAlignment(Pos.CENTER);

        btnTwoPlayer = new MenuButton("2 Player");
        btnRandom = new MenuButton("Random Player");
        btnAI = new MenuButton("AI Player");

        vbMenuButtons.getChildren().addAll(btnTwoPlayer, btnRandom, btnAI);

        root.setCenter(vbMenuButtons);
    }

    public Button getBtnTwoPlayer() {
        return btnTwoPlayer;
    }

    public Button getBtnRandom() {
        return btnRandom;
    }

    public Button getBtnAI() { return btnAI; }
}
