package it.unipv.posfw.orbit.payment;

import it.unipv.posfw.orbit.account.User;
import it.unipv.posfw.orbit.database.FacadeDB;
import it.unipv.posfw.orbit.exception.PaymentFailedException;
import it.unipv.posfw.orbit.game.Game;

public class CreditCard implements IPaymentMethod {

	// Parameters

	private String cardCode;
	private String securityCode;
	private String owner;

	// Constructor

	public CreditCard(String cardCode, String securityCode, String owner) {
		this.cardCode = cardCode;
		this.securityCode = securityCode;
		this.owner = owner;
	}

	// Methods

	@Override
	public boolean pay(double amount) {
		// Since we would have to connect to the debit card's bank system, which goes beyond our scope,
		// we will simulate the possibility of an error occurring by using Math.random()
		
		
		final double ERROR_RATE = 0.04; // Simulate a 4% error rate
		double randomValue = Math.random();
		
		if(randomValue >= ERROR_RATE) {
			return true; // Payment succeeded
		}
		else{
			return false; // Payment failed due some problem with the bank system
			
		}
	}

}
