package it.unipv.posfw.orbit.account;

public class Admin extends User {
	
	// parameters
	
	private int repScore;
	
	// constructors

	// constructors for new Admin
	public Admin(String nickname, String password) { 
		super(nickname, password);
		this.repScore = 100;
	}
	
	// costructor for already existing Admin
	public Admin(int id, String nickname, String password, double balance) {
		super(id, nickname, password, balance);
		this.repScore= 100;
	}
	
	// methods
	
	// -- UNIMPLEMENTED --
	public void tempBanUser (int minutes, User user) {
		// set the user's isBanned boolean to true and start a timer based on the minutes
		
	}
	
	// -- UNIMPLEMENTED --
	public void permaBanUser (User user) {
		user.setBanned(true);
	}
	
	// created a different method do underline the difference between
	// banning a normal user and a publisher who
	// doesn't respect the platform's Term Of Service
	
	// -- UNIMPLEMENTED --
	public void permaBanPublisher (Publisher publisher) {
		publisher.setBanned(true);
		// delete all the games published by the banned publisher from the database
	}

	// getters and setters
	
	protected int getRepScore() {
		return repScore;
	}

	protected void setRepScore(int repScore) {
		this.repScore = repScore;
	}
	
	
	
}
