package poker.version_text.model;

import java.util.ArrayList;
import java.util.Scanner;

import poker.version_text.PokerGame;

public class PokerGameModel {
	private final ArrayList<Player> players = new ArrayList<>();
	private DeckOfCards deck;
	 
	
	public PokerGameModel() {
		Scanner userName = new Scanner(System.in);
			 
			 for(int i=0 ; i < PokerGame.NUM_PLAYERS ; i++) { //Here we tried to add a scanner for changing the players name
			 players.add(new Player(userName));
			 
			 }
		
		deck = new DeckOfCards();
	}
	
	
	
	public Player getPlayer(int i) {
		return players.get(i);
	}
	
	public DeckOfCards getDeck() {
		return deck;
	}
}
