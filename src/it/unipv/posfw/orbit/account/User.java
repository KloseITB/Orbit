package it.unipv.posfw.orbit.account;

import it.unipv.posfw.orbit.exception.*;
import it.unipv.posfw.orbit.library.Library;
import it.unipv.posfw.orbit.payment.*;

public class User {
	
	// parameters
	
	protected int id; //protected because it is a Primary Key in the db
	private String nickname;
	private String password;
	private boolean isBanned;
	private boolean isLoggedIn;
	
	protected Library library;
	private double balance;
	
	// constructors 
	
	// constructor new user
	public User(String nickname, String password) {
		this.nickname = nickname;
		this.password = password;
		this.library = new Library();
		isBanned = false;
		isLoggedIn = false;
		balance = 0;
	}
	
	// constructor imported users from db (already have id and balance)
	public User(int id,String nickname, String password, double balance) {
		this.id = id;                                     
		this.nickname = nickname;
		this.password = password;
		this.library = new Library();
		this.balance = balance;
		isBanned = false;
		isLoggedIn = false;
		balance = 0;
	}
	
	// class Methods
	
	// adding funds via a classic payment method
	public <E extends IPaymentMethod> void addFunds(double amount, E paymentMethod) {

		try {
			balance += PaymentManager.Pay(amount, paymentMethod);
		}
		catch(AmountNotValidException anve) {
			// say via UI that the operation failed
		}
	}
	
	// adding funds via gift card
	public void addFunds (String giftCardCode) {
		
		it.unipv.posfw.orbit.database.FacadeDB facade = it.unipv.posfw.orbit.database.FacadeDB.getInstance();	    
	    
		try {
	        // check the gift card's existence
	        if (facade.checkGiftCard(giftCardCode)) {
	            
	            // take the gift card value
	            double amount = facade.getGiftCardValue(giftCardCode);
	            
	            this.balance += amount;
	            
	            // update the db with the new balance and removal of the card
	            facade.updateUserBalance(this);
	            facade.discardGiftCard(giftCardCode);
	           
	            System.out.println("Riscattati " + amount + " euro. Nuovo saldo: " + this.balance);
	        }
	    } catch (CodeNotFoundException e) {
	        // exception if card doesn't exist
	        System.out.println(e.getMessage());
	    }
	}
	
	public void removeFunds(double amount) {
		balance -= amount;
	}
	
	// Getter & Setter
	
	protected void setBanned(boolean isBanned) {
		this.isBanned = isBanned;
	}
	
	protected boolean isBanned() {
		return isBanned;
	}
	
	public int getId() {
		return id;
	}
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
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

	public void setLibrary(Library library) {
		this.library = library;
	}
	
	


}
