package it.unipv.posfw.orbit.UI;

import java.util.LinkedList;

import it.unipv.posfw.orbit.account.AccountManager;
import it.unipv.posfw.orbit.account.User;
import it.unipv.posfw.orbit.database.FacadeDB;
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
    
    // Methods
    
    // check if the user credentials exist and are correct
    public boolean loginUser(String nickname, String password) {
        try {
            AccountManager.getInstance().setCurrentUser(FacadeDB.getInstance().login(nickname, password));
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
    	AccountManager.getInstance().setCurrentUser(new User(nickname, password));
    	return true;
    }
    
    public void addGameToLibrary(User user, Game game) {
    	User currentUser = AccountManager.getInstance().getCurrentUser();
    	currentUser.getLibrary().addGame(game, user);
    }
    
    public void saveReview(Review newReview){
    	FacadeDB.getInstance().saveReview(newReview);
    }
    
    // getters and setters
    public LinkedList<Game> getCurrentUserGames(){
    	return getCurrentUser().getOwnedGames();
    }
    
    // get all the game available to be sold
    public LinkedList<Game> getCatalog(){
    	
    	LinkedList<Game> catalog = new LinkedList<>();
    			
    	for ( int id : FacadeDB.getInstance().getAllGameIds()) {
    		catalog.add(FacadeDB.getInstance().getGame(id));
    	}
    	
    	return catalog;
    }
    
    public User getCurrentUser() {
        return AccountManager.getInstance().getCurrentUser();
    }
    
    public boolean checkGiftCardCode(String code) {
    	
    	return AccountManager.getInstance().getCurrentUser().addFunds(code);
    }
}