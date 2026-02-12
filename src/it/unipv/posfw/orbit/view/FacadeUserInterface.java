package it.unipv.posfw.orbit.view;

import java.util.LinkedList;
import it.unipv.posfw.orbit.account.SingletonAccountManager;
import it.unipv.posfw.orbit.account.User;
import it.unipv.posfw.orbit.database.FacadeDB;
import it.unipv.posfw.orbit.exception.PlayerAlreadyExistException;
import it.unipv.posfw.orbit.exception.UserNotFoundException;
import it.unipv.posfw.orbit.exception.WrongPasswordException;
import it.unipv.posfw.orbit.game.Game;

public class FacadeUserInterface {
	
	// parameters
    private static FacadeUserInterface instance;
    
    // Singleton pattern
    private FacadeUserInterface() {}
    
    public static FacadeUserInterface getInstance() {
        if (instance == null) {
            instance = new FacadeUserInterface();
        }
        return instance;
    }
    
    // methods
    
    // check if the user credentials exist and are correct
    public boolean loginUser(String nickname, String password) {
        try {
            SingletonAccountManager.getInstance().setCurrentUser(FacadeDB.getInstance().login(nickname, password));
        } catch (UserNotFoundException e) {
            return false;
        } catch (WrongPasswordException e) {
        	return false;
        }
        
        return true;
    }
    
    // check if the user nickname is already taken. If not, it creates a new user
    // with the inserted credentials
    public boolean signupUser(String nickname, String password) {
    	
    	try {
			FacadeDB.getInstance().signup(new User(nickname, password));
		} catch (PlayerAlreadyExistException e) {
			return false;
		}
    	SingletonAccountManager.getInstance().setCurrentUser(new User(nickname, password));
    	return true;
    }
    
    
    // getters and setters
    public LinkedList<Game> getUserGames(User user){
    	LinkedList<Game> userGames = new LinkedList<>();
    	LinkedList<Integer> gamesId = FacadeDB.getInstance().getLibrary(user);
    	for (int gameId : gamesId){
    		userGames.add(FacadeDB.getInstance().getGame(gameId));
    	}
    	
    	return userGames;
    }
    
    // get all the game available to be sold
    public LinkedList<Game> getCatalog(){
    	
    	LinkedList<Game> catalog = new LinkedList<>();
    			
    	for ( int id : FacadeDB.getInstance().getAllGameIds()) {
    		catalog.add(FacadeDB.getInstance().getGame(id));
    	}
    	
    	return catalog;
    }
    
    public String getSessionUserNickname() {
        return SingletonAccountManager.getInstance().getCurrentUser().getNickname();
    }
    
    public User getSessionUser() {
        return SingletonAccountManager.getInstance().getCurrentUser();
    }
}