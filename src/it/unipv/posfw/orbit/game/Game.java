package it.unipv.posfw.orbit.game;

import it.unipv.posfw.orbit.account.*;
import it.unipv.posfw.orbit.discount.Discount;

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
	
	public void discount(int percentage) {
		// i used a temp variable to avoid any kind of errors related to the pre-discount price of the game
		double tmp = currentPrice;
		tmp = new Discount(percentage).calculateDiscount(tmp);
		currentPrice = tmp;
	}
	
	public <T extends User> void buy(T user) {
		/*
		 if ( user.balance < currentPrice)
		 	float missingAmount = currentPrice - user.balance
		  	ask to add funds to the user's account via User Interface
		  		if(user agrees to add funds) {
		  			PaymentOption methodChosen = ask for the payment method via User Interface and store it
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
