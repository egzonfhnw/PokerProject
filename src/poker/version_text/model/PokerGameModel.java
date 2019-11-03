package poker.version_text.model;

import java.util.ArrayList;
import java.util.Scanner;

import poker.version_text.PokerGame;
import poker.version_text.controller.PokerGameController;

public class PokerGameModel {
	public final static ArrayList<Player> players = new ArrayList<>();
	private DeckOfCards deck;
	public static int NUM_PLAYERS = 4;

	
	public PokerGameModel() {
		//PokerGameController.addPlayerNumDialogue();
		 
		
			 
		
		deck = new DeckOfCards();
	}
	public void generatePlayers() {

		for (int i = 0; i < PokerGameModel.NUM_PLAYERS; i++) {

			players.add(new Player("Player "+ String.valueOf(i+1)));

		}

	}
	
	public Player getPlayer(int i) {
		if(players.isEmpty()) {

			generatePlayers();

		}
		return getPlayers().get(i);
	}
	
	public DeckOfCards getDeck() {
		return deck;
	}
	public ArrayList<Player> evaluateWinner() {
		ArrayList<Player> highestPlayers = new ArrayList<>();
		for (Player p : players) {
			if (highestPlayers.isEmpty()) {
				highestPlayers.add(p);
			} else {
				if (p.compareTo(highestPlayers.get(0)) > 0) {
					highestPlayers.clear();
					highestPlayers.add(p);
				} else if (p.compareTo(highestPlayers.get(0)) == 0) {
					highestPlayers.add(p);
				}
			}
		}
		return highestPlayers;
	}

	/**
	 * @return the players
	 */
	public static ArrayList<Player> getPlayers() {
		return players;
	}

	
}
