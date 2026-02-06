package it.unipv.posfw.orbit.account;

import java.util.ArrayList;
import it.unipv.posfw.orbit.game.Game;
import it.unipv.posfw.orbit.library.Library;

public class Publisher extends User {
	
	// Parameters
	
	ArrayList<Game> publishedGames;
	
	// Constructors
	
	//costruttore nuovi publisher (libreria tolta perchè se la porta dietro da user)
	public Publisher(String nickname, String password) {
		super(nickname, password);
		this.publishedGames = new ArrayList<Game>();
	}
	//costruttore publisher già presenti nel db
	public Publisher(int id, String nickname, String password, double balance) { //AGGIUNTA ID
		super(id, nickname, password, balance);
		publishedGames = new ArrayList<Game>();
	}
	
	// Class Methods
	
	public void publishGame (String name, double basePrice, String genre) {
		Game game = new Game(name, basePrice, genre);
		publishedGames.add(game);
		// add game to the DB
		
	}
	
	
	
	// Getters & Setters
}
