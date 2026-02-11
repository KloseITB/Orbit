package it.unipv.posfw.orbit.database;

import it.unipv.posfw.orbit.account.User;
import java.util.List;
import it.unipv.posfw.orbit.exception.*;
import it.unipv.posfw.orbit.game.Game;

public class FacadeDB {
    
    private static FacadeDB instance;
    private SingletonDatabaseHelper db;

    // private constructor
    private FacadeDB() {
        // facade manage the SingletonDatabaseHelper instance
        this.db = SingletonDatabaseHelper.getInstance();
    }

    // singleton for the Facade
    public static FacadeDB getInstance() {
        if (instance == null) {
            instance = new FacadeDB();
        }
        return instance;
    }

    // account methods

    public User login(String nickname, String password) throws UserNotFoundException, WrongPasswordException{
        return db.login(nickname, password);
    }
    
    public void signup(User user) throws PlayerAlreadyExistException{
    	db.registerUser(user);
    }
    
    public void updateUserBalance(User user) {
        db.updateUserBalance(user);
    }

    // game methods

    public void registerGame(Game game, int publisherId) {
        db.registerGame(game, publisherId);
    }

    public void purchaseGame(User buyer, Game game) throws AmountNotValidException {
        db.executePurchase(buyer, game);
    }
    
    public List<Integer> getLibrary(User user){
    	return db.getLibrary(user);
    }
    
    public Game getGame(int gameId) {
    	return db.getGame(gameId);
    }

    // gift cards methods
    
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