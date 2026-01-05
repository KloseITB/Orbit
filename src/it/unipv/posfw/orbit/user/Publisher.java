package it.unipv.posfw.orbit.user;

import java.util.ArrayList;

import it.unipv.posfw.orbit.game.Game;
import it.unipv.posfw.orbit.library.Library;

public class Publisher extends User {
	
	// Parameters
	
	ArrayList<Game> gameArrayList;
	
	// Constructors
	
	public Publisher(String nickname, String password, Library userLibrary) {
		super(nickname, password, userLibrary);
		super.setBanned(false);
		gameArrayList = new ArrayList<Game>();
	}

	public Publisher(String nickname, String password) {
		super(nickname, password);
		super.library = new Library();
		super.setBanned(false);
		gameArrayList = new ArrayList<Game>();
	}
	
	// Class Methods
	
	public void publishGame (String name, double basePrice, ArrayList<String> tags) {
		Game game = new Game(name, basePrice, tags);
		gameArrayList.add(game);
		// add game to the DB
		
	}
	
	
	
	// Getters & Setters
}
