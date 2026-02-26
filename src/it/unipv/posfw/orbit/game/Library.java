package it.unipv.posfw.orbit.game;

import java.util.LinkedList;

import it.unipv.posfw.orbit.account.User;
import it.unipv.posfw.orbit.database.FacadeDB;
import it.unipv.posfw.orbit.exception.GameNotFoundException;

public class Library {
	
	// Parameters
	
	private LinkedList<Integer> gamesLinkedList;
	private User owner;
	
	// Constructors
	
	public Library (User user) {
		gamesLinkedList = new LinkedList<Integer>();
		owner = user;
	}
	
	// Class Methods
	
	public void addGame(Game game) {
		FacadeDB.getInstance().addGameToLibrary(this, game);
	}
	
	public void removeGame(Game game) throws GameNotFoundException{
		if (gamesLinkedList.contains(game.getId())) {
		FacadeDB.getInstance().removeGameFromLibrary(this, game);
		} else {
			throw new GameNotFoundException("Game not found");
		}
	}
	
	public LinkedList<Game> getGames() {
		LinkedList<Game> userGames = new LinkedList<>();
    	LinkedList<Integer> gamesId = FacadeDB.getInstance().getLibrary(owner);
    	for (int gameId : gamesId){
    		userGames.add(FacadeDB.getInstance().gameFromId(gameId));
    	}
    	
    	return userGames;
	}

	public User getOwner(){
		return owner;
	}
	
	public LinkedList<Integer> getGamesIdList(){
		return gamesLinkedList;
	}

}
