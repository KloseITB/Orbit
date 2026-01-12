package it.unipv.posfw.orbit.library;

import java.util.LinkedList;

public class Library {
	
	//parameters
	
	LinkedList<Integer> gamesLinkedList;
	
	// constructors
	
	public Library () {
		gamesLinkedList = new LinkedList<Integer>();
	}
	
	// class methods
	
	public void addGame(Integer gameID) {
		if(!gamesLinkedList.contains(gameID))
		gamesLinkedList.add(gameID);
		else {
			// tell that this game is already in the library
		}
	}
	

	public void removeGame(Integer gameID) {
		if ( gamesLinkedList.contains(gameID))
		gamesLinkedList.remove(gameID);
		else {
			// tell that the game has not been found in the library
		}
	}
	
	public void getOwnedGames() {

		// SQL query
	}

}
