package it.unipv.posfw.orbit.account;
import it.unipv.posfw.orbit.database.FacadeDB;
import it.unipv.posfw.orbit.exception.*;

public class AccountManager {
	
	private static AccountManager uniqueInstance;
	
	private User currentUser; // local variable to keep the user logged in
	
	// Constructors
	
	private AccountManager() {}

	// Get instance of the class
	public static AccountManager getInstance() {
		if (uniqueInstance == null ) {
			return uniqueInstance = new AccountManager();
		} else return uniqueInstance;
	}
	
	// Methods
	
	public boolean signup(String nickname, String password){
		
		User user = new User(nickname, password);
		
		try{
			FacadeDB.getInstance().signup(user);
			this.currentUser = user;
			return true;
		}
        catch(UserAlreadyExistException uee){
            return false;
        }
	}
	
	public boolean login(String nickname, String password) {
		try {
			// Call to the method that can handle exceptions
			User foundUser = FacadeDB.getInstance().login(nickname, password);
			
			// If no exceptions the login is successful 
			this.currentUser = foundUser;
			return true;
		} catch (UserNotFoundException e) {
			// user not found error
			return false;
		} catch (WrongPasswordException e) {
			// wrong password error
			return false;
		}
	}
	
	
	// Getters and Setters
	
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
