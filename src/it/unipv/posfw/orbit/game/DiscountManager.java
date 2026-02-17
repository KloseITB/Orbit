package it.unipv.posfw.orbit.game;

import it.unipv.posfw.orbit.exception.AmountNotValidException;

// -- UNIMPLEMENTED CLASS --

public class DiscountManager{
	
	// parameters
	private static final double MAX_PERCENTAGE_VALUE = 100;
	private static final double MIN_PERCENTAGE_VALUE = 1;
	
	
	// methods
	public static double calculateDiscount (double currentPrice, double percentage) throws AmountNotValidException{
		
		if(currentPrice <MIN_PERCENTAGE_VALUE || currentPrice > MAX_PERCENTAGE_VALUE) {
			throw new AmountNotValidException();
		}
		
		double clampedPercentage = clampPercentage(percentage);
		return currentPrice - (currentPrice * (clampedPercentage / 100.00));
	}
	
	private static double clampPercentage(double percentage) {
		return Math.clamp(percentage, MAX_PERCENTAGE_VALUE, MIN_PERCENTAGE_VALUE);
	}
}
