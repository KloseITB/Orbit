package it.unipv.posfw.orbit.account;

import java.util.ArrayList;

import it.unipv.posfw.orbit.game.Game;
import it.unipv.posfw.orbit.library.Library;

public class Publisher extends User {
	
	// Parameters
	private boolean hasLicense;
	ArrayList<Game> publishedGames;
	
	// Constructors

	public Publisher(String nickname, String password) {
		super(nickname, password);
		hasLicense = false;
		super.library = new Library();
		publishedGames = new ArrayList<Game>();
	}
	
	// Class Methods
	
	public void publishGame (String name, double basePrice, String genre) {
		Game game = new Game(name, basePrice, genre);
		publishedGames.add(game);
		// add game to the DB
		
	}
	
	public void buyLicense() {
		// to-do
	}
	
	// Getters & Setters
}
