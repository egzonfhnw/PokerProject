package poker.version_text.controller;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
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

		JPanel panel = new JPanel();
		JLabel label = new JLabel("Choose number of players: ");
		panel.setPreferredSize(new Dimension(400, 100));
        panel.add(label);
        Font font = new Font( "Arial", Font.BOLD, 25);
        label.setFont(font);
        DefaultComboBoxModel playerCount = new DefaultComboBoxModel();
        playerCount.addElement("2");
        playerCount.addElement("3");
        playerCount.addElement("4");
        JComboBox comboBox = new JComboBox(playerCount);
        comboBox.setFont(font);
        comboBox.setPreferredSize(new Dimension(50,50));
        panel.add(comboBox);

        int result = JOptionPane.showConfirmDialog(null, panel, "Number of players", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
      
        switch (result) {
            case JOptionPane.OK_OPTION:
            	int selected = Integer.parseInt((String) playerCount.getSelectedItem());
            	PokerGameModel.NUM_PLAYERS = selected;
            case JOptionPane.CANCEL_OPTION:
            	panel.setVisible(false);
            	break;
            	/*JOptionPane.OK_OPTION:
            	PokerGameModel.NUM_PLAYERS = result;
                break;*/
        }

    }

		
		
		/*JOptionPane frame = new JOptionPane("Number of players", JOptionPane.QUESTION_MESSAGE);
		
		String playerCount = JOptionPane.showInputDialog(frame, "Enter number of players");
		
		int iPlayerCount = Integer.parseInt(playerCount);

		PokerGameModel.NUM_PLAYERS = iPlayerCount;*/

	

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
		
		PokerGameController.addPlayerNumDialogue();
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
