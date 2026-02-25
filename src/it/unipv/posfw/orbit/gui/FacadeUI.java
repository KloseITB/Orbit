package it.unipv.posfw.orbit.gui;

import java.util.LinkedList;

import it.unipv.posfw.orbit.account.AccountManager;
import it.unipv.posfw.orbit.account.Publisher;
import it.unipv.posfw.orbit.account.Role;
import it.unipv.posfw.orbit.account.User;
import it.unipv.posfw.orbit.database.FacadeDB;
import it.unipv.posfw.orbit.exception.ReviewNotFoundException;
import it.unipv.posfw.orbit.exception.UserAlreadyExistException;
import it.unipv.posfw.orbit.exception.UserNotFoundException;
import it.unipv.posfw.orbit.game.Game;
import it.unipv.posfw.orbit.game.Review;

/**
 * facade class that connects the graphical user interface with the back-end logic.
 * implements the singleton pattern.
 */
public class FacadeUI {
	
	// Attributes
    private static FacadeUI instance;
    private User user = AccountManager.getInstance().getCurrentUser();
    private FacadeDB db = FacadeDB.getInstance();
    private AccountManager accMan = AccountManager.getInstance();
    
    // Singleton pattern
    private FacadeUI() {}
    
    public static FacadeUI getInstance() {
        if (instance == null) {
            instance = new FacadeUI();
        }
        return instance;
    }
    
    // Methods
    
    /**
     * attempts to log the user in from the GUI layer.
     * @param nickname the input nickname
     * @param password the input password
     * @return true if successful, false otherwise
     */
    public boolean loginUser(String nickname, String password) {

			if(accMan.login(nickname, password)){
				accMan.setCurrentUser(new User(nickname, password));
                return true;
            }
            else{
              return false;  
            }
    }
    
    // Check if the user nickname is already taken. If not, it creates a new user
    // with the inserted credentials
    public boolean signupUser(String nickname, String password) {
    	
			if(accMan.signup(nickname, password)){
				accMan.setCurrentUser(new User(nickname, password));
                return true;
            }
            else{
              return false;  
            } 
    }
    
    public void addGameToLibrary(User user, Game game) {
    	user.getLibrary().addGame(game);
    }
    
    public boolean saveReview(Review newReview){
    	Game game = db.gameFromId(newReview.getGameId()); // Get the game reviewed
    	game.addToReviewList(newReview); // Add the review to the game's list
        return true;
    }
    
    // get all the game available to be sold
    public LinkedList<Integer> getIdCatalog(){
    	
    	LinkedList<Integer> catalog = db.getAllGameIds();
    	return catalog;
    }
    
    public User getCurrentUser() {
        return user;
    }
    
    public Role getCurrentUserRole() {
    	return user.getRole();
    }
    
    public boolean checkGiftCardCode(String code) {
    	
    	return user.addGiftCardFunds(code);
    }
    
    /**
     * checks if a user is allowed to leave a review for a specific game.
     * @param gameId the id of the game
     * @param user the user trying to review
     * @return true if the user has not reviewed the game yet, false if they already did
     */
    public boolean checkReview(int gameId, User user) {
    	
    	try {
    		// check in the DB
    		db.checkReview(gameId, user);
    		// if we get here the exception is not thrown so the review exists
    		return false;
    	} catch(ReviewNotFoundException rnfe) {
    		// the exception was thrown so the review exists
    		return true;
    	}
    }

    public Game getGameFromId(int id){    
        return db.gameFromId(id);
    }

    public LinkedList<Game> getGameFromId(LinkedList<Integer> idList){    
        LinkedList<Game> gameList = new LinkedList<>();

        for (int id : idList){
            Game game = db.gameFromId(id);
            gameList.add(game);
        }

        return gameList;
    }
    
    public void publishGame(Game game) {
    	Publisher publisher = (Publisher) user;
    	db.registerGame(game, publisher.getId());
    	publisher.getLibrary().addGame(game);
    }
    
}