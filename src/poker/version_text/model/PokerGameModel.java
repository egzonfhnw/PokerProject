package poker.version_text.model;

import java.util.ArrayList;
import java.util.Scanner;

import poker.version_text.PokerGame;

public class PokerGameModel {
	private final ArrayList<Player> players = new ArrayList<>();
	private DeckOfCards deck;
	 
	
	public PokerGameModel() {
		Scanner userName = new Scanner(System.in);//Here we tried to add a scanner for changing the players name
		System.out.println("Enter the number of players");
		PokerGame.NUM_PLAYERS = userName.nextInt();//number of players
		System.out.println("Enter one name, click enter, and repeat this until all players have a name");
		for(int i=0 ; i < PokerGame.NUM_PLAYERS ; i++) { //inserting the names
		players.add(new Player(userName.next()));
			 
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
