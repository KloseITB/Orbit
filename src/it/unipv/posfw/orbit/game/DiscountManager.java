package it.unipv.posfw.orbit.game;

import it.unipv.posfw.orbit.exception.AmountNotValidException;

/**
 * utility class responsible for calculating game discounts.
 */

public class DiscountManager{
	
	// parameters
	private static final double MAX_PERCENTAGE_VALUE = 100;
	private static final double MIN_PERCENTAGE_VALUE = 1;
	
	
	// methods
	
	/**
	 * calculates the new price of a game after applying a percentage discount.
	 * @param currentPrice the original price of the game
	 * @param percentage the discount percentage to apply (must be between 1 and 100)
	 * @return the final calculated price
	 * @throws AmountNotValidException if the percentage is out of valid bounds
	 */
	public static double calculateDiscount (double currentPrice, double percentage) throws AmountNotValidException{
		
		if(currentPrice < 0) {
			throw new AmountNotValidException("the game price cannot be negative.");
		}
		
		double clampedPercentage = clampPercentage(percentage);
		return currentPrice - (currentPrice * (clampedPercentage / 100.00));
	}
	
	private static double clampPercentage(double percentage) {
		return Math.max(MIN_PERCENTAGE_VALUE, Math.min(MAX_PERCENTAGE_VALUE, percentage));
	}
}
