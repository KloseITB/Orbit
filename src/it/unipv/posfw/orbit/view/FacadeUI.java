package it.unipv.posfw.orbit.view;

import java.util.LinkedList;

import it.unipv.posfw.orbit.account.SingletonAccountManager;
import it.unipv.posfw.orbit.account.User;
import it.unipv.posfw.orbit.database.FacadeDB;
import it.unipv.posfw.orbit.exception.CodeNotFoundException;
import it.unipv.posfw.orbit.exception.PlayerAlreadyExistException;
import it.unipv.posfw.orbit.exception.UserNotFoundException;
import it.unipv.posfw.orbit.exception.WrongPasswordException;
import it.unipv.posfw.orbit.game.Game;
import it.unipv.posfw.orbit.game.Review;

public class FacadeUI {
	
	// parameters
    private static FacadeUI instance;
    
    // Singleton pattern
    private FacadeUI() {}
    
    public static FacadeUI getInstance() {
        if (instance == null) {
            instance = new FacadeUI();
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
    
    public void addGameToLibrary(User user, Game game) {
    	User currentUser = SingletonAccountManager.getInstance().getCurrentUser();
    	currentUser.getLibrary().addGame(game, user);
    }
    
    public void saveReview(Review newReview){
    	FacadeDB.getInstance().saveReview(newReview);
    }
    
    // getters and setters
    public LinkedList<Game> getSessionUserGames(){
    	return getSessionUser().getOwnedGames();
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
    
    public boolean checkGiftCardCode(String code) {
    	
    	return SingletonAccountManager.getInstance().getCurrentUser().addFunds(code);
    }
}