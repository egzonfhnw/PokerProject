package poker.version_text.view;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import poker.version_text.model.DeckOfCards;

public class ControlArea extends HBox{
    private DeckLabel lblDeck = new DeckLabel();
    private Region spacer = new Region(); // Empty spacer
    Button btnShuffle = new Button("Shuffle");
    Button btnDeal = new Button("Deal");
    Button btnPlayerSelection = new Button("Player Selection");
    //Button btnAddPlayer = new Button("Add Player");
    //Button btnReducePlayer = new Button("Reduce Player");

    public ControlArea() {
    	super(); // Always call super-constructor first !!
    	
    	this.getChildren().addAll(lblDeck, spacer, btnShuffle, btnDeal, btnPlayerSelection); //btnAddPlayer, btnReducePlayer);

        HBox.setHgrow(spacer, Priority.ALWAYS); // Use region to absorb resizing
        this.setId("controlArea"); // Unique ID in the CSS
    }
    
    public void linkDeck(DeckOfCards deck) {
    	lblDeck.setDeck(deck);
    }
}
