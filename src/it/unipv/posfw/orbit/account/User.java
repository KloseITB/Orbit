package it.unipv.posfw.orbit.account;

import java.util.ArrayList;
import java.util.LinkedList;

import it.unipv.posfw.orbit.exception.AmountNotValidException;
import it.unipv.posfw.orbit.exception.CodeNotFoundException;
import it.unipv.posfw.orbit.game.Game;
import it.unipv.posfw.orbit.library.Library;
import it.unipv.posfw.orbit.payment.IPaymentMethod;
import it.unipv.posfw.orbit.payment.PaymentManager;

public class User {
	
	// parameters
	protected int id; // protected because it is a Primary Key in the db
	protected Library library;
	private String nickname;
	private String password;
	private boolean isBanned;
	private boolean isAdmin;
	private boolean isPublisher;
	private double balance;
	private ArrayList<Game> publishedGames; // in case the user publishes a game
	
	
	// constructors

	// constructor new user
	public User(String nickname, String password) {
		this.nickname = nickname;
		this.password = password;
		this.library = new Library();
		isBanned = false;
		isAdmin = false;
		isPublisher = false;
		balance = 0;
	}
	
	// constructor imported users from the database (already have an id and a balance)
	public User(int id,String nickname, String password, double balance) {
		this.id = id;                                     
		this.nickname = nickname;
		this.password = password;
		this.library = new Library();
		this.balance = balance;
		isBanned = false;
		isPublisher = false;
		balance = 0;
	}
	
	// constructor that gives the possibility to set a new account as admin and/or publisher
	public User(String nickname, String password, boolean isAdmin, boolean isPublisher) {
		this.nickname = nickname;
		this.password = password;
		this.library = new Library();
		isBanned = false;
		this.isAdmin = isAdmin;
		this.isPublisher = isPublisher;
		balance = 0;
	}
	
	// methods
	
	// adding funds via a conventional payment method
	public <E extends IPaymentMethod> boolean addFunds(double amount, E paymentMethod) {

		try {
			balance += PaymentManager.Pay(amount, paymentMethod);
			return true;
		}
		catch(AmountNotValidException anve) {
			return false;
		}
	}
	
	// adding funds via gift card
	public boolean addFunds (String giftCardCode) {
		
		it.unipv.posfw.orbit.database.FacadeDB facade = it.unipv.posfw.orbit.database.FacadeDB.getInstance();	    
	    
		try {
	        // check the gift card's existence
	        if (facade.checkGiftCard(giftCardCode)) {
	            
	            // take the gift card value
	            double amount = facade.getGiftCardValue(giftCardCode);
	            
	            this.balance += amount;
	            
	            // update the database with the new balance and removal of the card
	            facade.updateUserBalance(this);
	            facade.discardGiftCard(giftCardCode);
	           
	            System.out.println("Riscattati " + amount + " euro. Nuovo saldo: " + this.balance);
	        }
	    } catch (CodeNotFoundException e) {
	        // exception if card doesn't exist
	        return false;
	    }
		
		return true;
	}
	
	
	public void removeFunds(double amount) {
		balance -= amount;
	}
	
	
	// ADMIN-SPECIFIC methods

	public void permaBanUser (User user) {
		setBanned(true, this);
	}
	
	// called when a game violates the platform's Term of Service (ex. NSFW content, Scam, AI slop ecc...)
	public void banPublishedGame(Game game) {
		if(isAdmin) {
			game.setBanned(true);
		}
		else {
			// error: the user is not an admin
		}
	}
	
	// getter and Setter
	
	// you can ban someone ONLY if you are an admin
	protected void setBanned(boolean isBanned, User admin) {
		if(admin.isAdmin) {
			this.isBanned = true;
		}
		else {
			// error: the user is not an admin
		}
	}
	
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
	
	public boolean getIsAdmin() {
		return isAdmin;
	}
	
	public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
	
	public void setPublisher(boolean isPublisher) {
        this.isPublisher = isPublisher;
    }
	
	public boolean getIsPubblisher() {
		return isPublisher;
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
