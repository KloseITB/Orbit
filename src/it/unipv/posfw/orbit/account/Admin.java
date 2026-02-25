package it.unipv.posfw.orbit.account;

import it.unipv.posfw.orbit.database.FacadeDB;
import it.unipv.posfw.orbit.game.Game;

/**
 * represents an administrator user with special privileges.
 * an admin can ban users or remove published games from the store.
 */
public class Admin extends User {

	// Constructors

	// Constructors for new Admin
	public Admin(String nickname, String password) { 
		super(nickname, password);
		role = Role.ADMIN;
	}
	
	// Costructor for already existing Admin
	public Admin(int id, String nickname, String password, double balance) {
		super(id, nickname, password, balance);
		role = Role.ADMIN;
	}
	
	// Methods
	
	/**
	 * bans a specific user from the platform, updating both local memory and database.
	 * @param user the user object to be banned
	 */
	public void banUser (User user) {
		user.setBanned(true);
		FacadeDB.getInstance().updateUserBanStatus(user, true);
	}
	
	/**
	 * bans a specific game from the platform, making it unavailable for purchase.
	 * @param game the game object to be banned
	 */
	public void banPublishedGame(int gameId) {
			Game game = FacadeDB.getInstance().gameFromId(gameId);
			game.setBanned(true);
			FacadeDB.getInstance().updateGameBanStatus(game, true);
	}
	
	
}
