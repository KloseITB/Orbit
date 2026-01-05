package it.unipv.posfw.orbit.user;

import it.unipv.posfw.orbit.library.Library;

public abstract class User {
	
	// Parameters
	
	private String nickname;
	private String password;
	private boolean isBanned;
	protected Library library;
	// private Balance balance
	
	// Constructors 
	
	public User(String nickname, String password, Library userLibrary) {
		this.nickname = nickname;
		this.password = password;
		this.library = userLibrary;
		this.isBanned = false;
	}
	
	public User(String nickname, String password) {
		this.nickname = nickname;
		this.password = password;
		this.library = new Library();
	}
	
	// Class Methods
	
	public void buyGame(int ID) {
		// check the game's price
		// check balance:
		
		/*
		 if (balance < game)
		  	ask to add funds to the user's account
		  	call the method from the class, that will be final, that handles payment options (Debit Card, PayLad (rip-off of PayPal), gift card code)
		 else {
		 	user.balance -= game.getPrice();
		 }
		 	

		 */
	}
	
	// Getter & Setter
	
	protected void setBanned(boolean isBanned) {
		this.isBanned = isBanned;
	}
	
	protected boolean isBanned() {
		return isBanned;
	}

}
