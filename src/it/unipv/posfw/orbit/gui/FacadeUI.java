package it.unipv.posfw.orbit.gui;

import java.util.LinkedList;

import it.unipv.posfw.orbit.account.AccountManager;
import it.unipv.posfw.orbit.account.User;
import it.unipv.posfw.orbit.database.FacadeDB;
import it.unipv.posfw.orbit.exception.UserAlreadyExistException;
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
        AccountManager.getInstance().login(nickname, password);
        
        return true;
    }
    
    // check if the user nickname is already taken. If not, it creates a new user
    // with the inserted credentials
    public boolean signupUser(String nickname, String password) {
    	
    	try {
			AccountManager.getInstance().signup(nickname, password);
		} catch (UserAlreadyExistException e) {
			return false;
		}
    	AccountManager.getInstance().setCurrentUser(new User(nickname, password));
    	return true;
    }
    
    public void addGameToLibrary(User user, Game game) {
    	User currentUser = AccountManager.getInstance().getCurrentUser();
    	currentUser.getLibrary().addGame(game);
    }
    
    public void saveReview(Review newReview){
    	Game game = FacadeDB.getInstance().getGameFromId(newReview.getGameId()); // Get the game reviewed
    	game.addToReviewList(newReview); // Add the review to the game's list
    }
    
    // getters and setters
    public LinkedList<Game> getCurrentUserGames(){
    	return getCurrentUser().getLibrary().getGames();
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

    public Game getGameFromId(int id){
        Game game = FacadeDB.getInstance().getGameFromId(id);
        
        return game;
    }

    public LinkedList<Game> getGameFromId(LinkedList<Integer> idList){    
        LinkedList<Game> gameList = new LinkedList<>();

        for (int id : idList){
            Game game = FacadeDB.getInstance().getGameFromId(id);
            gameList.add(game);
        }

        return gameList;
    }
}