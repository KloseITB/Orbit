package it.unipv.posfw.orbit.library;

import java.util.LinkedList;

import it.unipv.posfw.orbit.account.User;
import it.unipv.posfw.orbit.database.FacadeDB;
import it.unipv.posfw.orbit.exception.GameNotFoundException;
import it.unipv.posfw.orbit.game.Game;

public class Library {
	
	// Parameters
	
	LinkedList<Game> gamesLinkedList;
	
	// Constructors
	
	public Library () {
		gamesLinkedList = new LinkedList<Game>();
	}
	
	// Class Methods
	
	public void addGame(Game game, User user) {
		gamesLinkedList.add(game);
		//FacadeDB.getInstance().addGameToLibrary(user, game);
	}
	
	public void removeGame(Game game, User user) throws GameNotFoundException{
		if (gamesLinkedList.contains(game))
		gamesLinkedList.remove(game);
		//FacadeDB.getInstance().removeGameToLibrary(user, game);
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
