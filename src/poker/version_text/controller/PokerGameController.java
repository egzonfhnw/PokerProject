package poker.version_text.controller;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;

import javax.swing.JComboBox;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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

		view.getShuffleButton().setOnAction(e -> shuffle());
		view.getDealButton().setOnAction(e -> deal());
		view.getPlayerSelectionButton().setOnAction(e -> playerSelection()); //Pops window playerSelection

	}

	public static void addPlayerNumDialogue() {// Dialog which takes a string and converts it into an int of players

		JPanel panel = new JPanel();
		JLabel label = new JLabel("Choose number of players: ");
		panel.setPreferredSize(new Dimension(400, 100));
		panel.add(label);
		Font font = new Font("Arial", Font.BOLD, 25);
		label.setFont(font);
		DefaultComboBoxModel<String> playerCount = new DefaultComboBoxModel<String>();
		playerCount.addElement("2");
		playerCount.addElement("3");
		playerCount.addElement("4");
		JComboBox<String> comboBox = new JComboBox<String>(playerCount);
		comboBox.setFont(font);
		comboBox.setPreferredSize(new Dimension(50, 50));
		panel.add(comboBox);

		int result = JOptionPane.showConfirmDialog(null, panel, "Number of players", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE);

		switch (result) {
		case JOptionPane.OK_OPTION:
			int selected = Integer.parseInt((String) playerCount.getSelectedItem());
			PokerGameModel.NUM_PLAYERS = selected;
		case JOptionPane.CANCEL_OPTION:
			panel.setVisible(false);
			break;

		}

	}

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
			for (int i = 0; i < PokerGameModel.NUM_PLAYERS; i++) {
				view.getPlayerPane(i).updateWinners(model.evaluateWinner());
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR, "Not enough cards - please shuffle first");
			alert.showAndWait();
		}
	}

}
