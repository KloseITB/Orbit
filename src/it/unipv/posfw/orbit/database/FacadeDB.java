package it.unipv.posfw.orbit.database;

import java.util.LinkedList;
import it.unipv.posfw.orbit.account.User;
import it.unipv.posfw.orbit.exception.*;
import it.unipv.posfw.orbit.game.*;

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

    // Game methods

    public void registerGame(Game game, int publisherId) {
        db.registerGame(game, publisherId);
    }

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
}