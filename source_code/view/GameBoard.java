package view;

import controller.TwoPlayerController;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.ArrayList;

/**
 * Type of scene that represents the game board with 6 houses for each player, score arc for each player and SIDEBAR menu
 */
public class GameBoard extends Scene {
    //The container of the houses and the score arc
    private BorderPane bpGameBoard;
    //The containers for the house buttons
    private HBox hbPOneHouseHolder, hbPTwoHouseHolder;
    //The Arraylist of all houses
    private ArrayList<Button> alstAllHouses;
    //Score arc for each player's score
    private ScoreArc saScoreOne, saScoreTwo;
    //The controller for the game
    private TwoPlayerController controller;
    //The sidebar menu of the board
    private Sidebar sidebar;
    //The pane that has the seed animations
    private Pane animationPane;

    /**
     * Creates game board with houses, scores and sidebar
     * @param width The initial width of the scene
     * @param height The initial height of the scene
     * @param controller The Controller for the board passed by OwareUI
     */
    public GameBoard(double width, double height, TwoPlayerController controller) {
        super(new StackPane(), width, height);

        //Initiates the animation pain that is transparent and ignored by the mouse
        StackPane layerPane = (StackPane) this.getRoot();
    	animationPane = new Pane();
    	animationPane.setMouseTransparent(true);

        //Initialise controller
        this.controller = controller;

        //Create and initialise sidebar
        sidebar = new Sidebar(5);

        //Creates the root container with coloured background
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #1b2c47");

        //Creates border pane for the center of te board
        bpGameBoard = new BorderPane();
        root.setCenter(bpGameBoard);
        bpGameBoard.setStyle("-fx-background-color: #1b2c47");

        //Vertical box containing the containers or both players houses
        VBox vbHouseHolder = new VBox(20);
        vbHouseHolder.setAlignment(Pos.CENTER);

        //Horizontal box containing player one's buttons
        hbPOneHouseHolder = new HBox(10);
        hbPOneHouseHolder.setAlignment(Pos.CENTER);

        //Horizontal box containing player two's buttons
        hbPTwoHouseHolder = new HBox(10);
        hbPTwoHouseHolder.setAlignment(Pos.CENTER);

        //Adds containers of houses to vertical box
        vbHouseHolder.getChildren().addAll(hbPTwoHouseHolder, hbPOneHouseHolder);

        alstAllHouses = new ArrayList<>(12);

        //Creates and adds 6 house buttons for player one
        for (int i = 0; i < 6; ++i) {
            Button btnOneHouse = new HouseButton("");
            //Determines where the last seed from this button will land on hover
            hoverHouseButton(btnOneHouse);
            //Assigns controller even listener
            btnOneHouse.setOnAction(controller);
            btnOneHouse.setId("" + i);
            btnOneHouse.setDisable(true);
            hbPOneHouseHolder.getChildren().add(btnOneHouse);
            alstAllHouses.add(btnOneHouse);
        }

        //Creates and adds 6 house buttons for player two
        for (int i = 11; i > 5; --i) {
            Button btnTwoHouse = new HouseButton("");
            //Determines where the last seed from this button will land on hover
            hoverHouseButton(btnTwoHouse);
            //Assigns controller even listener
            btnTwoHouse.setOnAction(controller);
            btnTwoHouse.setId("" + i);
            btnTwoHouse.setDisable(true);
            hbPTwoHouseHolder.getChildren().add(btnTwoHouse);
            alstAllHouses.add(6, btnTwoHouse);
        }

        bpGameBoard.setCenter(vbHouseHolder);

        //Creates score arc for player 1
        saScoreOne = new ScoreArc(0, "0");

        //Adds player 1 score arc to board
        HBox hbArcOne = new HBox(saScoreOne);
        hbArcOne.setAlignment(Pos.CENTER);
        bpGameBoard.setBottom(hbArcOne);

        //Creates score arc for player 2
        saScoreTwo = new ScoreArc(180, "0");

        //Adds player 2 score arc to board
        HBox hbArcTwo = new HBox(saScoreTwo);
        hbArcTwo.setAlignment(Pos.CENTER);
        bpGameBoard.setTop(hbArcTwo);

        //Creates a sidebar menu button
        MenuButton btnToMenu = new MenuButton("Menu");
        btnToMenu.setPrefWidth(80);

        BorderPane bpToMenu = new BorderPane();

        //Adds menu button to top left of scene
        HBox hbToMenu = new HBox(btnToMenu);
        bpToMenu.setCenter(hbToMenu);
        hbToMenu.setStyle("-fx-padding: 5px");
        root.setLeft(bpToMenu);

        //Event handler for menu button that displays the sidebar and hides the menu button when displayed
        btnToMenu.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                bpToMenu.setLeft(sidebar);
                hbToMenu.getChildren().remove(btnToMenu);

                sidebar.getBtnCloseSB().setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        bpToMenu.getChildren().remove(sidebar);
                        hbToMenu.getChildren().add(btnToMenu);
                    }
                });
            }
        });

        //Creates a identical menu button on opposite end to create even bezels
        MenuButton btnEvenSpace = new MenuButton("Menu");
        btnEvenSpace.setPrefWidth(80);
        btnEvenSpace.setVisible(false);

        HBox hbEvenSpace = new HBox(btnEvenSpace);
        hbEvenSpace.setStyle("-fx-padding: 5px");
        root.setRight(hbEvenSpace);
        layerPane.getChildren().addAll(root,animationPane);
    }
    
    
    public void doAnimation(){
    	animationPane.getChildren().clear();
    	for (Button b : alstAllHouses){
    		//System.out.println(b.localToScene(b.getBoundsInLocal()).getMinX());
    		//System.out.println(b.getText());
    		int seedCount = Integer.parseInt(b.getText());
    		double duration = seedCount;    		
    		
    		for (int i=0; i<seedCount; i++){
    			Circle pathCircle = new Circle(b.localToScene(b.getBoundsInLocal()).getMinX()+50, b.localToScene(b.getBoundsInLocal()).getMinY()+50, 50);
    			pathCircle.setRotate(360/seedCount*i);
    			
    			Circle seed = new Circle(5, Paint.valueOf("white"));
        		PathTransition path = new PathTransition();
            	path.setNode(seed);
            	path.setDuration(Duration.seconds(duration));
            	path.setPath(pathCircle);
            	path.setCycleCount(PathTransition.INDEFINITE);
            	path.setInterpolator(Interpolator.LINEAR);
            	//path.setDelay(Duration.seconds(i*delay));
            	path.play();
            	animationPane.getChildren().addAll(seed);
    		}   
    	}    	
    }
    
    public void moveAnimation(int houseNumber){
    	System.out.println("Animate move");
    	int seeds = Integer.parseInt(alstAllHouses.get(houseNumber).getText());
    	System.out.println("House " + houseNumber + ", seeds: " + seeds);
    	int nextHouse = houseNumber;
    	int i = 1;
    	while(i <= seeds) {
            if((++nextHouse) % 12 == houseNumber)
                continue;
            shootSeed(houseNumber, nextHouse, 0.2*i, (i+1)>=seeds);
            ++i;
        }    	
    }
    
    public void shootSeed(int fromHouse, int toHouse, double delay, boolean lastHouse){
    	toHouse = toHouse%12;
    	System.out.println("Shooting a seed from " + fromHouse + " to " + toHouse);
    	Button fromButton = alstAllHouses.get(fromHouse);
    	Button toButton = alstAllHouses.get(toHouse);
    	double xFrom = 50 + fromButton.localToScene(fromButton.getBoundsInLocal()).getMinX();
    	double yFrom = 50 + fromButton.localToScene(fromButton.getBoundsInLocal()).getMinY();
    	double xTo = 50 + toButton.localToScene(toButton.getBoundsInLocal()).getMinX();
    	double yTo = 50 + toButton.localToScene(toButton.getBoundsInLocal()).getMinY();
    	Line linePath = new Line(xFrom, yFrom, xTo, yTo);

		Circle seed = new Circle(5, Paint.valueOf("white"));
    	PathTransition path = new PathTransition();
    	path.setNode(seed);
    	path.setDuration(Duration.seconds(delay));
    	path.setPath(linePath);
    	path.setCycleCount(1);
    	path.setInterpolator(Interpolator.EASE_OUT);
    	path.setDelay(Duration.seconds(delay));
    	path.play();
    	animationPane.getChildren().addAll(seed);
    	if(lastHouse){
	    	path.setOnFinished(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					doAnimation();
					
				}
			});
    	}
    	//doAnimation();
    	
    }

    private void hoverHouseButton(Button btnHouse) {
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

        btnHouse.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnHouse.setStyle(sBtnStyle + "-fx-border-color: #1692cc;");

                Button other = alstAllHouses.get(calcHighlight(btnHouse));

                other.setStyle(other.getStyle() + "-fx-border-width: 5px;");
            }
        });

        btnHouse.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnHouse.setStyle(sBtnStyle);

                Button other = alstAllHouses.get(calcHighlight(btnHouse));
                other.setStyle(other.getStyle() + "-fx-border-width: 1px;");

            }
        });

        btnHouse.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                removeHouseHighlighting();
            }
        });
    }

    private int calcHighlight(Button button) {
        int noToMove = Integer.parseInt(button.getText());

        int startingIndex = -1;
        for (Button btn : alstAllHouses) {
            if (btn.getId() == button.getId()) {
                startingIndex = alstAllHouses.indexOf(btn);
            }
        }

        int n =  noToMove/12;
        noToMove += startingIndex;
        noToMove += n;
        noToMove %= 12;

        return noToMove;
    }

    private void removeHouseHighlighting() {
        for (Button b : alstAllHouses) b.setStyle(b.getStyle() + "-fx-border-width: 1px;");
    }

    /**
     * Enable the button house for a given house of player 1
     * @param houseIn The house to enable
     */
    public void enableP1House(int houseIn) {
        if ((houseIn >= 0) || (houseIn < 6)) {
            for (Node b : hbPOneHouseHolder.getChildren()) {
                if (houseIn == Integer.parseInt(b.getId())) b.setDisable(false);
            }
        }
    }

    /**
     * Enable the button house for a given house of player 2
     * @param houseIn The house to enable
     */
    public void enableP2House(int houseIn) {
        if ((houseIn >= 0) || (houseIn < 6)) {
            for (Node b : hbPTwoHouseHolder.getChildren()) {
                if (houseIn == Integer.parseInt(b.getId())) b.setDisable(false);
            }
        }
    }

    /**
     * Update no. of seeds in a given house of player 1
     * @param houseID The house to update
     * @param value The value to update the house to
     */
    public void updateHouseP1(int houseID, int value) {
        for (Node b : hbPOneHouseHolder.getChildren()) {
            if (Integer.parseInt(b.getId()) == houseID) {
                Button btn = (Button) b;
                btn.setText(""+ value);
            }
        }
    }

    /**
     * Update no. of seeds in a given house of player 1
     * @param houseID The house to update
     * @param value The value to update the house to
     */
    public void updateHouseP2(int houseID, int value) {
        for (Node b : hbPTwoHouseHolder.getChildren()) {
            if (Integer.parseInt(b.getId()) == houseID) {
                Button btn = (Button) b;
                btn.setText(""+ value);
            }
        }
    }

    /**
     * Updates scores of both players
     * @param scoreP1 Score of player 1
     * @param scoreP2 Score of player 2
     */
    public void updateScores(int scoreP1, int scoreP2) {
        saScoreOne.updateScore(scoreP1);
        saScoreTwo.updateScore(scoreP2);
    }

    /**
     * Disables all of Player 1's buttons
     */
    public void disableAllP1() {
        for (Node b : hbPOneHouseHolder.getChildren()) {
            b.setDisable(true);
        }
    }

    /**
     * Disables all of player 2's button
     */
    public void disableAllP2() {
        for (Node b : hbPTwoHouseHolder.getChildren()) {
            b.setDisable(true);
        }
    }

    /**
     * Grants access to the sidebar
     * @return The Sidebar of the board
     */
    public Sidebar getSidebar() {
        return sidebar;
    }

}
