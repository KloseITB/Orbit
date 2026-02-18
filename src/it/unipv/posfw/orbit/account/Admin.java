package it.unipv.posfw.orbit.account;

import it.unipv.posfw.orbit.game.Game;

public class Admin extends User {

	// Constructors

	// Constructors for new Admin
	public Admin(String nickname, String password) { 
		super(nickname, password);
	}
	
	// Costructor for already existing Admin
	public Admin(int id, String nickname, String password, double balance) {
		super(id, nickname, password, balance);
	}
	
	// Methods
	
	public void banUser (User user) {
		user.isBanned = true;
	}
	
	// Called when a game violates the platform's Term of Service (ex. NSFW content, Scam, AI slop ecc...)
	public void banPublishedGame(Game game) {
			game.setBanned(true);
	}
	
	
}
