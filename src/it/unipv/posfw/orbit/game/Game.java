package it.unipv.posfw.orbit.game;

import java.util.ArrayList;

import it.unipv.posfw.orbit.account.*;
import it.unipv.posfw.orbit.discount.DiscountManager;
import it.unipv.posfw.orbit.exception.AmountNotValidException;

public class Game {

	// parameters
	
	private int id;
	private String title;
	private double basePrice;
	private double currentPrice;
	private String genre;

	private ArrayList<Review> reviewArrayList = new java.util.ArrayList<>();

	private String coverPath; // string with the directory of the game's cover image

	
	// Constructors
	
	public Game(String name, double price, String tags, String coverPath) {
		this.id = 0; // temporary id, the final one will be given by the database 
		this.title = name;
		this.basePrice = price;
		this.currentPrice = this.basePrice;
		this.genre = tags;
		this.coverPath = coverPath;
	}
	
	
	// methods
	public void discount(double percentage) {
		// i used a temporary variable to avoid any kind of errors related to the pre-discount price of the game
		double tmp = currentPrice;
		DiscountManager discountManager =  new DiscountManager();
		try {
			tmp = discountManager.calculateDiscount(tmp, percentage);
		}
		catch (AmountNotValidException ave) {
			// say via UI that the amount isn't acceptable and that it will be clamped into a number between 1% and 100%
		}
		currentPrice = tmp;
	}
	
	public <T extends User> void buy(T user) {
		
		if (user.getBalance() < this.currentPrice) {
			double missing= this.currentPrice - user.getBalance();
			System.out.println("Mancano " + missing +" fondi.");
			// UI asking if you want to add balance
			
			return;
		}
		try {
			it.unipv.posfw.orbit.database.FacadeDB.getInstance().purchaseGame(user, this);
			System.out.println("Gioco acquistato: " + this.title);
		}catch (Exception e) {
			System.out.println("Errore durante l'acquisto: " + e.getMessage());
		}
		
		/*
		 if ( user.balance < currentPrice)
		 	float missingAmount = currentPrice - user.balance
		  	ask to add funds to the user's account via User Interface
		  		if(user agrees to add funds) {
		  			PaymentOptions methodChosen = ask for the payment method via User Interface and store it
		  			user.addFunds(missingAmount, methodChosen);
		  		}
		  else {
		 	 user.removeFunds(currentPrice);
		 	 add game to the user's library inside the Database
		  }
		 */
	}
	
	// getters and setters
	
	public double getCurrentPrice() {
		return currentPrice;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getGenre() {
		return genre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}
	

    public void scrivirecensione(Review r) {
        this.reviewArrayList.add(r);
       
        
        System.out.println("Recensione aggiunta: " + r.toString());
        
    }

    public java.util.ArrayList<Review> getrecensioni() {
        return this.reviewArrayList;
    }
	
	public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }
	
	
}
