package it.unipv.posfw.orbit.account;
import it.unipv.posfw.orbit.database.FacadeDB;
import it.unipv.posfw.orbit.exception.*;

public class SingletonAccountManager {
	
	private static SingletonAccountManager uniqueInstance;
	
	private User currentUser; // local variable to keep the user logged in
	
	// Constructors
	
	private SingletonAccountManager() {}

	
	// Get instance of the class
	public static SingletonAccountManager getInstance() {
		if (uniqueInstance == null ) {
			return uniqueInstance = new SingletonAccountManager();
		} else return uniqueInstance;
	}
	
	// Methods
	
	public User signup(String nickname, String password) throws PlayerAlreadyExistException{
		
		User user = new User(nickname, password);
		
		// we try the registration through the facade
		FacadeDB.getInstance().signup(user);
		
		// if we get here the registration was successful
		this.currentUser = user; // current user set as logged in
		
		System.out.println("Registration successful: " + nickname); // debug
		
		return user;
	}
	
	public void login(String nickname, String password) {
		try {
			// call to the method that can handle exceptions
			User foundUser = FacadeDB.getInstance().login(nickname, password);
			
			// if no exceptions the login is successful 
			this.currentUser = foundUser;
			
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
		
		// for debug purposes
		if(currentUser == null) {
			login("PlayerOne", "password");
		}
		return currentUser;
	}
	
	public void setCurrentUser(User user) {
		this.currentUser = user;
	}
	
}
