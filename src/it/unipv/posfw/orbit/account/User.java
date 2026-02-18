package it.unipv.posfw.orbit.account;

import java.util.ArrayList;
import java.util.LinkedList;

import it.unipv.posfw.orbit.database.FacadeDB;
import it.unipv.posfw.orbit.exception.CodeNotFoundException;
import it.unipv.posfw.orbit.exception.PaymentFailedException;
import it.unipv.posfw.orbit.game.Game;
import it.unipv.posfw.orbit.game.Library;
import it.unipv.posfw.orbit.payment.IPaymentMethod;

public class User {
	
	// parameters
	protected int id; // Primary Key in the db
	protected Library library;
	private String nickname;
	private String password;
	protected boolean isBanned; // pro
	private double balance;
	private ArrayList<Game> publishedGames; // in case the user publishes a game
	
	
	// constructors

	// constructor new user
	public User(String nickname, String password) {
		this.nickname = nickname;
		this.password = password;
		this.library = new Library(this);
		isBanned = false;
		balance = 0;
	}
	
	// constructor imported users from the database (already have an id and a balance)
	public User(int id,String nickname, String password, double balance) {
		this.id = id;                                     
		this.nickname = nickname;
		this.password = password;
		this.library = new Library(this);
		isBanned = false;
		this.balance = balance;
		balance = 0;
	}

	
	// Methods
	
	// Adding funds via conventional payment method
	public <E extends IPaymentMethod> boolean addFunds(double amount, E paymentMethod) {

		try {
			balance += paymentMethod.pay();
			return true;
		}
		catch(PaymentFailedException pfe) {
			return false;
		}
	}
	
	// Adding funds via gift card
	public boolean addFunds (String giftCardCode) {
		
		FacadeDB.getInstance();    
	    
		try {
	        // Check the gift card's existence
	        if (FacadeDB.getInstance().checkGiftCard(giftCardCode)) {
	            
	            // Take the gift card value
	            double amount = FacadeDB.getInstance().getGiftCardValue(giftCardCode);
	            
	            this.balance += amount;
	            
	            // Update the database with the new balance and removal of the card
	            FacadeDB.getInstance().updateUserBalance(this);
	            FacadeDB.getInstance().discardGiftCard(giftCardCode);
	           
	            System.out.println("Redeemed " + amount + " Euros. New Balance: " + this.balance);
	        }
	    } catch (CodeNotFoundException e) {
	        // Exception if card doesn't exist
	        return false;
	    }
		
		return true;
	}
	
	
	public void removeFunds(double amount) {
		balance -= amount;
	}
	

	// Getter and Setter
	
	protected boolean isBanned() {
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

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Library getLibrary() {
		return library;
	}
	
	// similar to getLibrary but it outputs an iterable list of games owned by the user
	public LinkedList<Game> getOwnedGames() {
		return library.getGames(this);
	}

	public void setLibrary(Library library) {
		this.library = library;
	}
	
	public ArrayList<Game> getPublishedGames() {
		return publishedGames;
	}

	public void setPublishedGames(ArrayList<Game> publishedGames) {
		this.publishedGames = publishedGames;
	}
	


}
