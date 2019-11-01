package poker.version_text.view;

import javafx.scene.Scene;

import java.util.Scanner;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import poker.version_text.PokerGame;
import poker.version_text.model.Player;
import poker.version_text.model.PokerGameModel;

public class PokerGameView {
	public static HBox players;
	private ControlArea controls;
	
	private PokerGameModel model;
	
	/*"public SelectPlayer(Stage stage1) {
		Scanner userName = new Scanner(System.in);	    
	    String playerName = userName.nextLine(); 
		TextField name1 = new TextField();
	}*/
	public Stage stage = null;
	public PokerGameView(Stage stage, PokerGameModel model) {
	this.model = model;
		
		// Create all of the player panes we need, and put them into an HBox.
		setPlayers(new HBox());
		for (int i = 0; i < PokerGameModel.NUM_PLAYERS; i++) {
			PlayerPane pp = new PlayerPane();
			HBox.setHgrow(pp, Priority.ALWAYS); // On window resize, expand the player panes
			pp.setPlayer(model.getPlayer(i)); // link to player object in the logic
			getPlayers().getChildren().add(pp);
		}
		
		// Create the control area
		controls = new ControlArea();
		controls.linkDeck(model.getDeck()); // link DeckLabel to DeckOfCards in the logic
		
		// Put players and controls into a BorderPane
		BorderPane root = new BorderPane();
		root.setCenter(getPlayers());
		root.setBottom(controls);

        // Create the scene using our layout; then display it
        Scene scene = new Scene(root);
        scene.getStylesheets().add(
                getClass().getResource("poker.css").toExternalForm());
        this.stage = stage;
        stage.setTitle("Poker Miniproject");
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
        stage.setResizable(false);
        
        
	}
	
	public PlayerPane getPlayerPane(int i) {
		return (PlayerPane) getPlayers().getChildren().get(i);
	}
	
	/*public Button getAddPlayerButton() {
		return controls.btnAddPlayer;
	}
	
	public Button getReducePlayerButton() {
		return controls.btnReducePlayer;
	}*/
	
	public void addPlayerToView(Player player) {
		PlayerPane pp = new PlayerPane();
		pp.setPlayer(player);
		players.getChildren().add(pp);
	}
	
	public Button getShuffleButton() {
		return controls.btnShuffle;
	}
	
	public Button getDealButton() {
		return controls.btnDeal;
	}
	public Button getPlayerSelectionButton() {
		return controls.btnPlayerSelection;
	}
	public HBox getPlayers() {
		return players;
	}

	public void setPlayers(HBox players) {
		this.players = players;
	}
	public void show() {
		stage.show();
	}
}
