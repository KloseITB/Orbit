package it.unipv.posfw.orbit.game;

import it.unipv.posfw.orbit.account.*;
import it.unipv.posfw.orbit.discount.DiscountManager;
import it.unipv.posfw.orbit.exceptions.AmountNotValidException;

public class Game {

	// Parameters
	
	private int id;
	private String title;
	private double basePrice;
	private double currentPrice;
	private String genre;
	
	// Constructors
	
	public Game(String name, double price, String tags) {
		// this.id = method that generates the game's ID based on how many games already exist
		this.basePrice = price;
		this.currentPrice = this.basePrice;
		this.genre = tags;
	}
	
	// Class Methods
	
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
	
	// Getters & Setters
	
	public double getCurrentPrice() {
		return currentPrice;
	}
	
	
}
