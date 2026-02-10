package it.unipv.posfw.orbit.main;

import it.unipv.posfw.orbit.account.User;

public class SessionManager {

	private User currentUser = new User("Klose", "klosepassword"); // user set just for debug reasons

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	
	

}
