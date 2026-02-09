package it.unipv.posfw.orbit.library;

import java.util.LinkedList;

import it.unipv.posfw.orbit.exception.GameNotFoundException;

public class Library {
	
	// Parameters
	
	LinkedList<Integer> gamesLinkedList;
	
	// Constructors
	
	public Library () {
		gamesLinkedList = new LinkedList<Integer>();
	}
	
	// Class Methods
	
	public void addGame(Integer gameID) {
		if(!gamesLinkedList.contains(gameID))
		gamesLinkedList.add(gameID);
		// add game to the library of the user in SQL
		else {
			// tell that this game is already in the library
		}
	}
	
	public void removeGame(Integer gameID) throws GameNotFoundException{
		if (gamesLinkedList.contains(gameID))
		gamesLinkedList.remove(gameID);
		// remove game to the library of the user in SQL
		else {
			throw new GameNotFoundException("\n" + "Game not found");
		}
	}
	
	public void getOwnedGames() {
		// get game info through SQL query
	}

}
