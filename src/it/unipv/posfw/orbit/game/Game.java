package it.unipv.posfw.orbit.game;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

import javax.swing.ImageIcon;

import it.unipv.posfw.orbit.account.*;
import it.unipv.posfw.orbit.database.FacadeDB;
import it.unipv.posfw.orbit.exception.AmountNotValidException;
import it.unipv.posfw.orbit.exception.PaymentFailedException;
import it.unipv.posfw.orbit.payment.IPaymentMethod;

/**
 * represents a video game available on the orbit platform.
 * it manages price, discounts, reviews and purchasing logic.
 */
public class Game {
	
	// parameters
	
	private int id;
	private String title;
	private String genre;
	private String coverPath; // Directory path of the game's cover image
	private double basePrice;
	private double currentPrice;
	private boolean isBanned; // Is the game still available for purchase?
	private ArrayList<Review> reviewArrayList = new ArrayList<>();

	
	// Constructors
	
	public Game(String name, double price, String tags, String coverPath) {
		this.id = 0; // temporary id, the final one will be given by the database 
		this.title = name;
		this.basePrice = price;
		this.currentPrice = this.basePrice;
		this.genre = tags;
		this.coverPath = coverPath;
	}
	
	
	// Methods
	
	/**
	 * applies a percentage discount to the current price of the game.
	 * @param percentage the discount value to apply
	 */
	public void discount(double percentage) {
		double tmp = currentPrice; // Used a temporary variable to avoid any kind of errors related to the pre-discount price of the game
		try {
			tmp = DiscountManager.calculateDiscount(tmp, percentage);
		}
		catch (AmountNotValidException ave) {
			// say via UI that the amount isn't acceptable and that it will be clamped into a number between 1% and 100%
		}
		currentPrice = tmp;
	}
	
	/**
	 * allows a user to buy the game using their account balance.
	 * @param user the user purchasing the game
	 * @throws PaymentFailedException if the user does not have enough funds
	 */
	public <U extends User> void buy(U user) throws PaymentFailedException{
		try {
			FacadeDB.getInstance().purchaseGame(user, this);
		} catch (AmountNotValidException e) {
			throw new PaymentFailedException("Insufficient balance or system error");
		}
	}
	
	/**
	 * allows a user to buy the game using a specific payment method.
	 * @param user the user purchasing the game
	 * @param paymentMethod the credit card or external payment method used
	 * @throws PaymentFailedException if the transaction fails
	 */
	public <U extends User,P extends IPaymentMethod> void buy(U user, P paymentMethod) throws PaymentFailedException{
		if(paymentMethod.pay(this.currentPrice)) {
			user.getLibrary().addGame(this);
		}
		else {
			throw new PaymentFailedException();
		}
	}
	
	public void addToReviewList(Review review) {
		reviewArrayList.add(review);
		try {
			FacadeDB.getInstance().saveReview(review);
		} catch (Exception e) {
			reviewArrayList.remove(review); // local rollback
			System.out.println("Error saving review.");
		}
	}
	
	/**
	 * calculates the average rating of the game based on user reviews.
	 * @return the average rating score, or 0 if no reviews exist
	 */
	public double avgRating() {
		
		double ratingSum = 0;
		int reviewCount = 0;
		double avgRating = 0;
		
		// For every review, get the rating score
		for (Review review : reviewArrayList) {
			ratingSum += review.getRating();
			reviewCount++;
		}
		
		// Try-Catch in case there are no reviews, which means that the code will try to divide 0 by 0
		try{
			avgRating = ratingSum / reviewCount;
			}
		catch(Exception e) {
			return 0; // Returning 0 means that the game hasn't been reviewed yet
		}
		return avgRating;
	}
	
	
	// Getters and Setters
	public ImageIcon getCoverImg() {
		URL coverImageURL = getClass().getResource(coverPath);
		return new ImageIcon(coverImageURL);
	}
	
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

	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}
	
	public String getCoverPath() {
        return coverPath;
    }
	
	public boolean isBanned() {
		return isBanned;
	}

	public void setBanned(boolean isBanned) {
		this.isBanned = isBanned;
	}
    
    // Overrides the equals(Object o) method to confront 2 games by their id and not by their instances in the memory
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        
        if (o == null || getClass() != o.getClass()) return false;
        
        // 3. Casting dell'oggetto
        Game game = (Game) o;
        
        return this.id == game.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
	
}
