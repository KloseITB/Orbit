package it.unipv.posfw.orbit.library;

import java.util.LinkedList;

import it.unipv.posfw.orbit.account.User;
import it.unipv.posfw.orbit.database.FacadeDB;
import it.unipv.posfw.orbit.exception.GameNotFoundException;
import it.unipv.posfw.orbit.game.Game;

public class Library {
	
	// Parameters
	
	LinkedList<Integer> gamesLinkedList;
	
	// Constructors
	
	public Library () {
		gamesLinkedList = new LinkedList<Integer>();
	}
	
	// Class Methods
	
	public void addGame(int gameID) {
		if(!gamesLinkedList.contains(gameID))
		gamesLinkedList.add(gameID);
		// add game to the library of the user in SQL
		else {
			// tell that this game is already in the library
		}
	}
	
	public void removeGame(int gameID) throws GameNotFoundException{
		if (gamesLinkedList.contains(gameID))
		gamesLinkedList.remove(gameID);
		// remove game to the library of the user in SQL
		else {
			throw new GameNotFoundException("\n" + "Game not found");
		}
	}
	
	public LinkedList<Game> getGames(User user) {
		LinkedList<Game> userGames = new LinkedList<>();
    	LinkedList<Integer> gamesId = FacadeDB.getInstance().getLibrary(user);
    	for (int gameId : gamesId){
    		userGames.add(FacadeDB.getInstance().getGame(gameId));
    	}
    	
    	return userGames;
	}

}
