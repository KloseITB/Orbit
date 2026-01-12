package it.unipv.posfw.orbit.account;

public class SingletonAccountManager {
	
	private static SingletonAccountManager uniqueInstance;
	// Constructors
	
	private SingletonAccountManager() {}
	
	// Get instance of the class
	public static SingletonAccountManager getInstance() {
		if (uniqueInstance == null ) {
			return uniqueInstance = new SingletonAccountManager();
		} else return uniqueInstance;
	}
	
	// Methods
	
	public User signup(String nickname, String password) {
		User user = new User(nickname, password);
		return user;
	}
	
	public void login(String nickname, String password) {
	 // SQL
		/* 1. Query for finding the user with that nickname
		 * 2. if it finds the user, check if the password input is equal to the user's password
		 * 3. if everything is correct, link that user to a local variable
		 * 4. use setLoggedIn = true;
		 */
	}
}
