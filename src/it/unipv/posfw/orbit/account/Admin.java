package it.unipv.posfw.orbit.account;

public class Admin extends User {
	
	// Parameters
	
	private int repScore;
	
	// Constructors

	
	public Admin(String nickname, String password) {
		super(nickname, password);
		this.repScore = 100;
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
