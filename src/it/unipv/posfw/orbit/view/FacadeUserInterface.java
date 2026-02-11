package it.unipv.posfw.orbit.view;

import java.util.LinkedList;
import java.util.List;

import it.unipv.posfw.orbit.account.SingletonAccountManager;
import it.unipv.posfw.orbit.account.User;
import it.unipv.posfw.orbit.database.FacadeDB;
import it.unipv.posfw.orbit.exception.UserNotFoundException;
import it.unipv.posfw.orbit.exception.WrongPasswordException;
import it.unipv.posfw.orbit.game.Game;

public class FacadeUserInterface {

    private static FacadeUserInterface instance;

    private FacadeUserInterface() {}

    public static FacadeUserInterface getInstance() {
        if (instance == null) {
            instance = new FacadeUserInterface();
        }
        return instance;
    }

    public String getSessionUserNickname() {
        return SingletonAccountManager.getInstance().getCurrentUser().getNickname();
    }
    
    public User getSessionUser() {
        return SingletonAccountManager.getInstance().getCurrentUser();
    }

    public boolean setSessionUser(String nickname, String password) {
        try {
            FacadeDB.getInstance().login(nickname, password);
        } catch (UserNotFoundException e) {
            return false;
        } catch (WrongPasswordException e) {
        	return false;
        }
        
        return true;
    }
    
    public boolean signupUser(String nickname, String password) {
    	
    	// signup the player by adding it to the DB
    	return true;
    }
    
    public LinkedList<Game> getUserGames(User user){
    	LinkedList<Game> userGames = new LinkedList<>();
    	//LinkedList<Integer> gamesId = FacadeDB.getInstance().getLibrary(user);
    	//for (int gameId : gamesID){
    		//userGames.add(getGame(gameId));
    	//}
    	
    	return userGames;
    }
}