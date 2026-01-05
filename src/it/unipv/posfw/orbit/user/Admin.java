package it.unipv.posfw.orbit.user;

import it.unipv.posfw.orbit.library.Library;

public class Admin extends User {
	
	// Parameters
	
	private int repScore;
	
	// Constructors
	
	public Admin(String nickname, String password, Library library) {
		super(nickname, password, library);
		super.setBanned(false);
		this.repScore = 100;
	}
	
	public Admin(String nickname, String password) {
		super(nickname, password);
		super.setBanned(false);
		this.repScore = 100;
		super.library = new Library();
	}
	
	// Class Methods
	
	public void tempBanPlayer (int minutes, User user) {
		// set the user's isBanned boolean to true and start a timer based on the minutes
		
	}
	
	public void permaBanPlayer (User user) {
		// set the user's isBanned boolean to true
	}

	// Getters & Setters
	
	protected int getRepScore() {
		return repScore;
	}

	protected void setRepScore(int repScore) {
		this.repScore = repScore;
	}
	
	
	
}
