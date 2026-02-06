package it.unipv.posfw.orbit.account;

public class Admin extends User {
	
	// Parameters
	
	private int repScore;
	
	// Constructors

	//costruttore per nuovi admin (id e balance verranno creati dal db)
	public Admin(String nickname, String password) { 
		super(nickname, password);
		this.repScore = 100;
	}
	
	//costruttore per admin già esistenti nel db
	public Admin(int id, String nickname, String password, double balance) {
		super(id, nickname, password, balance);
		this.repScore= 100;
	}
	
	// Class Methods
	
	public void tempBanUser (int minutes, User user) {
		// set the user's isBanned boolean to true and start a timer based on the minutes
		
	}
	
	public void permaBanUser (User user) {
		user.setBanned(true);
	}
	
	// created a different method do underline the difference between banning a normal user and a publisher who -
	// doesn't respect the platform's TOS
	public void permaBanPublisher (Publisher publisher) {
		publisher.setBanned(true);
		// delete all the games published by the banned publisher from the database
	}

	// Getters & Setters
	
	protected int getRepScore() {
		return repScore;
	}

	protected void setRepScore(int repScore) {
		this.repScore = repScore;
	}
	
	
	
}
