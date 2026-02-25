package it.unipv.posfw.orbit.database;

import java.util.LinkedList;
import it.unipv.posfw.orbit.account.User;
import it.unipv.posfw.orbit.exception.*;
import it.unipv.posfw.orbit.game.*;

/**
 * facade class that provides a simplified interface to interact with the database helper.
 * implements the singleton pattern.
 */
public class FacadeDB {
    
    private static FacadeDB instance;
    private DatabaseHelper db;

    // Private constructor
    private FacadeDB() {
        // Facade manage the SingletonDatabaseHelper instance
        this.db = DatabaseHelper.getInstance();
    }

    // Singleton for the Facade
    public static FacadeDB getInstance() {
        if (instance == null) {
            instance = new FacadeDB();
        }
        return instance;
    }

    // Account methods
    
    /**
     * authenticates a user by querying the database.
     * @param nickname the user's nickname
     * @param password the user's password
     * @return the populated user object if found
     * @throws UserNotFoundException if the nickname does not exist
     * @throws WrongPasswordException if the password does not match
     */
    public User login(String nickname, String password) throws UserNotFoundException, WrongPasswordException{
        return db.login(nickname, password);
        
    }
    
    public void signup(User user) throws UserAlreadyExistException{
        db.registerUser(user);     
    }
    
    public void updateUserBalance(User user) {
        db.updateUserBalance(user);
    }
    
    public void addGameToLibrary(Library library, Game game) {
        db.addGameToLibrary(library, game);
    }

    public void removeGameFromLibrary(Library library, Game game) {
        db.removeGameFromLibrary(library, game);
    }
    
    public void updateUserBanStatus(User user, boolean isBanned) {
    	db.updateUserBanStatus(user, isBanned);
    }

    // Game methods

    public void registerGame(Game game, int publisherId) {
        db.registerGame(game, publisherId);
    }
    
    /**
     * executes the purchase transaction of a game safely.
     * @param buyer the user buying the game
     * @param game the game to be purchased
     * @throws AmountNotValidException if the user has insufficient balance
     */
    public void purchaseGame(User buyer, Game game) throws AmountNotValidException {
        db.executePurchase(buyer, game);
    }
    
    public LinkedList<Integer> getLibrary(User user){
    	return db.getLibrary(user);
    }
    
    public Game gameFromId(int gameId) {
    	return db.getGame(gameId);
    }
    
    public LinkedList<Integer> getAllGameIds() {
        return db.getAllGameIds();
    }
    
    public void saveReview(Review review) {
    	db.saveReview(review);
    }
    
    public void updateGameBanStatus(Game game, boolean isBanned) {
    	db.updateGameBanStatus(game, isBanned);
    }

    // Gift cards methods
    
    public boolean checkGiftCard(String code) throws CodeNotFoundException {
        return db.checkGiftCard(code);
    }

    public double getGiftCardValue(String code) {
        return db.getGiftCardValue(code);
    }

    public void discardGiftCard(String code) {
        db.discardGiftCard(code);
    }
    
    // Review methods
    
    public void checkReview(int gameId, User user) throws ReviewNotFoundException{
    	if (!db.hasUserReviewedGame(gameId, user.getId())) {
            // throw exception of the review doesn't exist, the user can still review the game
            throw new ReviewNotFoundException("No review found for " + gameId + " by " + user.getNickname());
        }
    }
    
    /**
	 * checks if a specific game is currently banned from the platform by querying the database.
	 * @param gameId the unique identifier of the game to check
	 * @return true if the game exists and is banned, false otherwise
	 */
	public boolean findGameById(int gameId) {
		return db.findGameById(gameId);
	}

	/**
	 * checks if a specific user is currently banned from the platform by querying the database.
	 * @param nickname the unique nickname of the user to check
	 * @return true if the user exists and is banned, false otherwise
	 */
	public boolean findUser(String nickname) {
		return db.findUser(nickname);
	}
}