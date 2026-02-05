package it.unipv.posfw.orbit.discount;

import it.unipv.posfw.orbit.exceptions.AmountNotValidException;

public class DiscountManager{
	
	public final double MAXPERCENTAGEVALUE = 100;
	public final double MINPERCENTAGEVALUE = 1;
	
	public double calculateDiscount (double currentPrice, double percentage) throws AmountNotValidException{
		
		if(currentPrice < MINPERCENTAGEVALUE || currentPrice > MAXPERCENTAGEVALUE) {
			throw new AmountNotValidException();
		}
		
		double clampedPercentage = clampPercentage(percentage);
		return currentPrice - (currentPrice * (clampedPercentage / 100.00));
	}
	
	private double clampPercentage(double percentage) {
		return Math.clamp(percentage, MAXPERCENTAGEVALUE, MINPERCENTAGEVALUE);
	}
}
