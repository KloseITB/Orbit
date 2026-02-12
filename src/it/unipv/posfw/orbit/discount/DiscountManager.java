package it.unipv.posfw.orbit.discount;

import it.unipv.posfw.orbit.exception.AmountNotValidException;

// -- UNIMPLEMENTED CLASS --

public class DiscountManager{
	
	// parameters
	public static final double MAXPERCENTAGEVALUE = 100;
	public static final double MINPERCENTAGEVALUE = 1;
	
	
	// methods
	public static double calculateDiscount (double currentPrice, double percentage) throws AmountNotValidException{
		
		if(currentPrice <MINPERCENTAGEVALUE || currentPrice > MAXPERCENTAGEVALUE) {
			throw new AmountNotValidException();
		}
		
		double clampedPercentage = clampPercentage(percentage);
		return currentPrice - (currentPrice * (clampedPercentage / 100.00));
	}
	
	private static double clampPercentage(double percentage) {
		return Math.clamp(percentage, MAXPERCENTAGEVALUE, MINPERCENTAGEVALUE);
	}
}
