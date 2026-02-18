package it.unipv.posfw.orbit.game;

import java.util.LinkedList;

import it.unipv.posfw.orbit.account.User;
import it.unipv.posfw.orbit.database.FacadeDB;
import it.unipv.posfw.orbit.exception.GameNotFoundException;

public class Library {
	
	// Parameters
	
	LinkedList<Game> gamesLinkedList;
	User owner;
	
	// Constructors
	
	public Library (User user) {
		gamesLinkedList = new LinkedList<Game>();
		owner = user;
	}
	
	// Class Methods
	
	public void addGame(Game game) {
		gamesLinkedList.add(game);
		FacadeDB.getInstance().addGameToLibrary(this, game);
	}
	
	public void removeGame(Game game, User user) throws GameNotFoundException{
		if (gamesLinkedList.contains(game)) {
		gamesLinkedList.remove(game);
		FacadeDB.getInstance().removeGameFromLibrary(this, game);
		} else {
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

	public User getOwner(){
		return owner;
	}

}
