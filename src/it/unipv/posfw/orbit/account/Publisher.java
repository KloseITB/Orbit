package it.unipv.posfw.orbit.account;

import java.util.ArrayList;

import it.unipv.posfw.orbit.database.FacadeDB;
import it.unipv.posfw.orbit.game.Game;

/**
 * represents a publisher user who can publish new games on the platform.
 */
public class Publisher extends User {
	
	// Parameters
	private ArrayList<Integer> publishedGames;
	private boolean hasLicense;

	// Constructors
	
	// Constructor for new publisher (no library because is brought by the user)
	public Publisher(String nickname, String password) {
		super(nickname, password);
		role = Role.PUBLISHER;
		this.publishedGames = new ArrayList<Integer>();
		hasLicense = false;
	}

	// Constructor publisher already existing in the db
	public Publisher(int id, String nickname, String password, double balance) {
		super(id, nickname, password, balance);
		role = Role.PUBLISHER;
		publishedGames = new ArrayList<Integer>();
		hasLicense = false;
	}
	
	
	// Methods
	
	/**
	 * publishes a new game on the store and registers it in the database.
	 * @param name the title of the game
	 * @param basePrice the initial price of the game
	 * @param genre the genre or tag of the game
	 * @param coverPath the file path to the game's cover image
	 */
	public void publishGame (String name, double basePrice, String genre, String coverPath) {
		
		Game game = new Game(name, basePrice, genre, coverPath); // Create the new game in memory with temporary id set to 0
		
		// We connect to the db to save the new game and to
		// get the id. The logged ID will be saved in this.id
	    FacadeDB.getInstance().registerGame(game, this.id);
	    
	    // We add it to the java memory now that we have the final id
	    if (game.getId() != 0) {
	        publishedGames.add(game.getId());
	        System.out.println("Game added to the publisher's published games."); // Debug
	    } else {
	        System.out.println("Error while trying to save the game's info."); // Debug
	    }
	}
	

	// Getters and Setters

	public ArrayList<Integer> getPublishedGamesId(){
		return publishedGames;
	}
	
	public boolean getLicense() {
		return hasLicense;
	}
	
	public void setLicense(boolean hasLicense) {
		this.hasLicense = hasLicense;
	}
}
