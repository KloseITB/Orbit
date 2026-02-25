package it.unipv.posfw.orbit.account;
import it.unipv.posfw.orbit.database.FacadeDB;
import it.unipv.posfw.orbit.exception.*;

/**
 * a singleton class that manages the current active session of the user.
 * it handles login and signup operations.
 */
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
	
	/**
	 * attempts to register a new user in the system.
	 * @param nickname the chosen username
	 * @param password the chosen password
	 * @return true if the registration is successful, false if the user already exists
	 */
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
	
	/**
	 * attempts to authenticate an existing user.
	 * @param nickname the username to check
	 * @param password the password to check
	 * @return true if credentials are correct, false otherwise
	 */
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
	
	public <U extends User> U getCurrentUser() {
		
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
