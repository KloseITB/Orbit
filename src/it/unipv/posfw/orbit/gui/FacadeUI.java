package it.unipv.posfw.orbit.gui;

import java.util.LinkedList;

import it.unipv.posfw.orbit.account.AccountManager;
import it.unipv.posfw.orbit.account.User;
import it.unipv.posfw.orbit.database.FacadeDB;
import it.unipv.posfw.orbit.exception.ReviewNotFoundException;
import it.unipv.posfw.orbit.exception.UserAlreadyExistException;
import it.unipv.posfw.orbit.exception.UserNotFoundException;
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
    
    // Check if the user credentials exist and are correct
    public boolean loginUser(String nickname, String password) {

			if(AccountManager.getInstance().login(nickname, password)){
                AccountManager.getInstance().setCurrentUser(new User(nickname, password));
                return true;
            }
            else{
              return false;  
            } 

    }
    
    // check if the user nickname is already taken. If not, it creates a new user
    // with the inserted credentials
    public boolean signupUser(String nickname, String password) {
    	
			if(AccountManager.getInstance().signup(nickname, password)){
                AccountManager.getInstance().setCurrentUser(new User(nickname, password));
                return true;
            }
            else{
              return false;  
            } 
    }
    
    public void addGameToLibrary(User user, Game game) {
    	User currentUser = AccountManager.getInstance().getCurrentUser();
    	currentUser.getLibrary().addGame(game);
    }
    
    public boolean saveReview(Review newReview){
    	Game game = FacadeDB.getInstance().gameFromId(newReview.getGameId()); // Get the game reviewed
    	game.addToReviewList(newReview); // Add the review to the game's list
        return true;
    }
    
    // get all the game available to be sold
    public LinkedList<Integer> getIdCatalog(){
    	
    	LinkedList<Integer> catalog = FacadeDB.getInstance().getAllGameIds();
    	return catalog;
    }
    
    public User getCurrentUser() {
        return AccountManager.getInstance().getCurrentUser();
    }
    
    public boolean checkGiftCardCode(String code) {
    	
    	return AccountManager.getInstance().getCurrentUser().addGiftCardFunds(code);
    }
    
    public boolean checkReview(int gameId, User user) {
    	
    	try {
    		// check in the DB
    		FacadeDB.getInstance().checkReview(gameId, user);
    		// if we get here the exception is not thrown so the review exists
    		return false;
    	} catch(ReviewNotFoundException rnfe) {
    		// the exception was thrown so the review exists
    		return true;
    	}
    }

    public Game getGameFromId(int id){    
        return FacadeDB.getInstance().gameFromId(id);
    }

    public LinkedList<Game> getGameFromId(LinkedList<Integer> idList){    
        LinkedList<Game> gameList = new LinkedList<>();

        for (int id : idList){
            Game game = FacadeDB.getInstance().gameFromId(id);
            gameList.add(game);
        }

        return gameList;
    }
}