package it.unipv.posfw.orbit.game;

import java.util.LinkedList;

import it.unipv.posfw.orbit.account.User;
import it.unipv.posfw.orbit.database.FacadeDB;
import it.unipv.posfw.orbit.exception.GameNotFoundException;

public class Library {
	
	// Parameters
	
	LinkedList<Integer> gamesLinkedList;
	User owner;
	
	// Constructors
	
	public Library (User user) {
		gamesLinkedList = new LinkedList<Integer>();
		owner = user;
	}
	
	// Class Methods
	
	public void addGame(Game game) {
		gamesLinkedList.add(game.getId());
		FacadeDB.getInstance().addGameToLibrary(this, game);
	}
	
	public void removeGame(Game game, User user) throws GameNotFoundException{
		if (gamesLinkedList.contains(game.getId())) {
		gamesLinkedList.remove(game.getId());
		FacadeDB.getInstance().removeGameFromLibrary(this, game);
		} else {
			throw new GameNotFoundException("\n" + "Game not found");
		}
	}
	
	public LinkedList<Game> getGames() {
		LinkedList<Game> userGames = new LinkedList<>();
    	LinkedList<Integer> gamesId = FacadeDB.getInstance().getLibrary(owner);
    	for (int gameId : gamesId){
    		userGames.add(FacadeDB.getInstance().getGameFromId(gameId));
    	}
    	
    	return userGames;
	}

	public User getOwner(){
		return owner;
	}

}
