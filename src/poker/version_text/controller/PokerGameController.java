package poker.version_text.controller;

import java.util.Optional;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import poker.version_text.PokerGame;
import poker.version_text.model.Card;
import poker.version_text.model.DeckOfCards;
import poker.version_text.model.Player;
import poker.version_text.model.PokerGameModel;
import poker.version_text.view.PlayerPane;
import poker.version_text.view.PokerGameView;

public class PokerGameController {
	private PokerGameModel model;
	private PokerGameView view;

	public PokerGameController(PokerGameModel model, PokerGameView view) {
		this.model = model;
		this.view = view;

		// view.getAddPlayerButton().setOnAction( e -> addPlayer() );
		view.getShuffleButton().setOnAction(e -> shuffle());
		view.getDealButton().setOnAction(e -> deal());
		view.getPlayerSelectionButton().setOnAction(e -> playerSelection());

	}

	/*
	 * private void addPlayer() { //Added a player, still have to add cards, name
	 * and functionality. PokerGameModel.NUM_PLAYERS++; PlayerPane pp = new
	 * PlayerPane(); PokerGameView.players.getChildren().add(pp); }
	 */

	/**
	 * Remove all cards from players hands, and shuffle the deck
	 */

	public static void addPlayerNumDialogue() {// Dialog which takes a string and converts it into an int of players

		JFrame frame = new JFrame("Number of players");

		String playerCount = JOptionPane.showInputDialog(frame, "Enter number of players");

		int iPlayerCount = Integer.parseInt(playerCount);

		PokerGameModel.NUM_PLAYERS = iPlayerCount;

	}

	/*
	 * public Player addPlayer() { Player newPlayer = model.addPlayer(userName);
	 * PokerGameView.addPlayerToView = (newPlayer); return newPlayer; }
	 */

	private void shuffle() {
		for (int i = 0; i < PokerGameModel.NUM_PLAYERS; i++) {
			Player p = model.getPlayer(i);
			p.discardHand();
			PlayerPane pp = view.getPlayerPane(i);
			pp.updatePlayerDisplay();
		}

		model.getDeck().shuffle();
	}

	private void playerSelection() { 
		model = new PokerGameModel();
		view = new PokerGameView(view.stage, model);
		PokerGameController controller = new PokerGameController(model, view);
	}

	/**
	 * Deal each player five cards, then evaluate the two hands
	 */
	private void deal() {
		int cardsRequired = PokerGameModel.NUM_PLAYERS * Player.HAND_SIZE;
		DeckOfCards deck = model.getDeck();
		if (cardsRequired <= deck.getCardsRemaining()) {
			for (int i = 0; i < PokerGameModel.NUM_PLAYERS; i++) {
				Player p = model.getPlayer(i);
				p.discardHand();
				for (int j = 0; j < Player.HAND_SIZE; j++) {
					Card card = deck.dealCard();
					p.addCard(card);
				}
				p.evaluateHand();
				PlayerPane pp = view.getPlayerPane(i);
				pp.updatePlayerDisplay();
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR, "Not enough cards - shuffle first");
			alert.showAndWait();
		}
	}

}
