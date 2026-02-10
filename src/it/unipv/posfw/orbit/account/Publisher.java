package it.unipv.posfw.orbit.account;

import java.util.ArrayList;
import it.unipv.posfw.orbit.game.Game;
import it.unipv.posfw.orbit.library.Library;

public class Publisher extends User {
	
	// Parameters
	
	ArrayList<Game> publishedGames;
	
	// Constructors
	
	// constructor of new publisher (no library because is brought by the user)
	public Publisher(String nickname, String password) {
		super(nickname, password);
		this.publishedGames = new ArrayList<Game>();
	}
	// constructor publisher already existing in the db
	public Publisher(int id, String nickname, String password, double balance) {
		super(id, nickname, password, balance);
		publishedGames = new ArrayList<Game>();
	}
	
	// Class Methods
	
	public void publishGame (String name, double basePrice, String genre) {
		
		Game game = new Game(name, basePrice, genre); // create the new game in memory with temporary id set to 0
		
		// we connect to db to save the new game and to get the id, the id logged will be saved in this.id
	    it.unipv.posfw.orbit.database.SingletonDatabaseHelper.getInstance().registerGame(game, this.id);
	    
	    // we add it to the java memory now that we have the final id
	    if (game.getId() != 0) {
	        publishedGames.add(game);
	        System.out.println("Gioco aggiunto alla lista locale del publisher.");
	    } else {
	        System.out.println("Errore nel salvataggio del gioco.");
	    }
	}
	
	
	
	// Getters & Setters
}
