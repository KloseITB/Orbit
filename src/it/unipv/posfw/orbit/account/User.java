package it.unipv.posfw.orbit.account;

import it.unipv.posfw.orbit.library.Library;

public class User {
	
	// Parameters
	
	private String nickname;
	private String password;
	private boolean isBanned;
	private boolean isLoggedIn;
	
	protected Library library;
	private float balance;
	
	// Constructors 

	public User(String nickname, String password) {
		this.nickname = nickname;
		this.password = password;
		this.library = new Library();
		isBanned = false;
		isLoggedIn = false;
		balance = 0f;
	}
	
	// Class Methods
	
	public void addFunds(float amount) {
		balance += amount;
	}
	
	public void removeFunds(float amount) {
		balance -= amount;
	}
	
	// Getter & Setter
	
	protected void setBanned(boolean isBanned) {
		this.isBanned = isBanned;
	}
	
	protected boolean isBanned() {
		return isBanned;
	}
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}


}
