package it.unipv.posfw.orbit.game;

import java.util.ArrayList;

import it.unipv.posfw.orbit.discount.Discount;

public class Game {

	// Parameters
	
	private int id;
	private String name;
	private double basePrice;
	private double currentPrice;
	private ArrayList<String> tagArrayList;
	
	// Constructors
	
	public Game(String name, double price, ArrayList<String> tags) {
		// this.id = method that generates the game's ID based on how many games already exist
		this.basePrice = price;
		this.currentPrice = this.basePrice;
		this.tagArrayList = tags;
	}
	
	// Class Methods
	
	public void discount(int percentage) {
		// i used a temp variable to avoid any kind of errors related to the pre-discount price of the game
		double tmp = currentPrice;
		tmp = new Discount(percentage).calculateDiscount(tmp);
		currentPrice = tmp;
	}
	
	public void addTag(String tag) {
		if (!tagArrayList.contains(tag)) {
			tagArrayList.add(tag);
		}
		else {
			// error: the tag already exists
		}
	}
	
	public void removeTag(String tag) {
		if (tagArrayList.contains(tag)) {
			tagArrayList.remove(tag);
		}
		else {
			// error: the tag doesn't exist	
		}
		
	}
	
	// Getters & Setters
	
	public double getCurrentPrice() {
		return currentPrice;
	}
	
	
}
