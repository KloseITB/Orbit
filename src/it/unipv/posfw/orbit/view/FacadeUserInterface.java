package it.unipv.posfw.orbit.view;

import it.unipv.posfw.orbit.account.SingletonAccountManager;
import it.unipv.posfw.orbit.account.User;
import it.unipv.posfw.orbit.database.FacadeDB;
import it.unipv.posfw.orbit.exception.UserNotFoundException;
import it.unipv.posfw.orbit.exception.WrongPasswordException;

public class FacadeUserInterface {

    private static FacadeUserInterface instance;

    private FacadeUserInterface() {}

    public static FacadeUserInterface getInstance() {
        if (instance == null) {
            instance = new FacadeUserInterface();
        }
        return instance;
    }

    public String getSessionUser() {
        return SingletonAccountManager.getInstance().getCurrentUser().getNickname();
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
}