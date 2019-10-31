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
		 
		for(int i =0; i<NUM_PLAYERS;i++) {//give every player a name
			players.add(new Player("Player "+ String.valueOf(i+1)));
		}
			 
		
		deck = new DeckOfCards();
	}
	
	public Player getPlayer(int i) {
		return getPlayers().get(i);
	}
	
	public DeckOfCards getDeck() {
		return deck;
	}

	/**
	 * @return the players
	 */
	public static ArrayList<Player> getPlayers() {
		return players;
	}

	
}
