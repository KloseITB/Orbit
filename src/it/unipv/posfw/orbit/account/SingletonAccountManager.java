package it.unipv.posfw.orbit.account;
import it.unipv.posfw.orbit.exception.*;

public class SingletonAccountManager {
	
	private static SingletonAccountManager uniqueInstance;
	
	private User currentUser; // local variable to keep the user logged in
	
	// Constructors
	
	private SingletonAccountManager() {
		this.currentUser = new User("Klose", "password"); // here just for debug purposes.
	}
	
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
		try {
			// call to the method that can handle exceptions
			User foundUser = it.unipv.posfw.orbit.database.FacadeDB.getInstance().login(nickname, password);
			
			// if no exceptions the login is successful 
			this.currentUser = foundUser;
			System.out.println("Login effettuato con successo: " + currentUser.getNickname());
			
		} catch (UserNotFoundException e) {
			// user not found error
			System.out.println("Errore: " + e.getMessage());
			
		} catch (WrongPasswordException e) {
			// wrong password error
			System.out.println("Errore: " + e.getMessage());
		}
	}
	
	
	// getters and setters
	
	public User getCurrentUser() {
		return currentUser;
	}
	
	public void setCurrentUser(User user) {
		this.currentUser = user;
	}
	
}
