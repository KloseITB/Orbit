package it.unipv.posfw.orbit.account;

import it.unipv.posfw.orbit.database.FacadeDB;
import it.unipv.posfw.orbit.exception.CodeNotFoundException;
import it.unipv.posfw.orbit.game.Library;

/**
 * represents a standard user in the orbit platform.
 * it contains user credentials, balance, library and ban status.
 */
public class User {
	
	// Parameters
	protected int id; // Primary Key in the db
	protected Library library;
	private String nickname;
	private String password;
	protected boolean isBanned;
	private double balance;
	protected Role role;
	
	// Constructors

	// Constructor new user
	public User(String nickname, String password) {
		this.nickname = nickname;
		this.password = password;
		this.library = new Library(this);
		role = Role.USER;
		isBanned = false;
		balance = 0;
	}
	
	// Constructor imported users from the database (already have an id and a balance)
	public User(int id,String nickname, String password, double balance) {
		this.id = id;                                     
		this.nickname = nickname;
		this.password = password;
		this.library = new Library(this);
		role = Role.USER;
		isBanned = false;
		this.balance = balance;
		balance = 0;
	}

	
	// Methods
	
	/**
	 * adds funds to the user's balance using a gift card code.
	 * @param giftCardCode the unique code of the gift card
	 * @return true if the gift card is valid and redeemed, false otherwise
	 */
	public boolean addGiftCardFunds (String giftCardCode) { 
	    
		try {
	        // Check the gift card's existence
	        if (FacadeDB.getInstance().checkGiftCard(giftCardCode)) {
	            
	            // Take the gift card value
	            double amount = FacadeDB.getInstance().getGiftCardValue(giftCardCode);
	            
	            this.balance += amount;
	            
	            // Update the database with the new balance and removal of the card
	            FacadeDB.getInstance().updateUserBalance(this);
	            FacadeDB.getInstance().discardGiftCard(giftCardCode);
	           
	            System.out.println("Redeemed " + amount + " Euros. New Balance: " + this.balance); // Debug
	        }
	    } catch (CodeNotFoundException e) {
	        return false;
	    }
		
		return true;
	}
	
	/**
	 * removes a specific amount of funds from the user's balance.
	 * @param amount the value to deduct from the balance
	 */
	public void removeFunds(double amount) {
		balance -= amount;
		FacadeDB.getInstance().updateUserBalance(this);
	}
	

	// Getters and Setters
	
	public boolean isBanned() {
		return isBanned;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public double getBalance() {
		return balance;
	}

	public Library getLibrary() {
		return library;
	}

	public void setLibrary(Library library) {
		this.library = library;
	}
	
	public void setBalanceLocal(double balance) {
		this.balance = balance;
	}
	
	public void setBanned(boolean isBanned) {
		this.isBanned = isBanned;
	}
	
	public Role getRole() {
		return role;
	}
	
}
