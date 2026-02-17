package it.unipv.posfw.orbit.payment;

import it.unipv.posfw.orbit.exception.PaymentFailedException;

public class DebitCard implements IPaymentMethod {

	@Override
	public double pay() throws PaymentFailedException {
		// Since we would have to connect to the debit card's bank system, which goes beyond our scope,
		// we will simulate the possibility of an error occurring by using Math.random()
		
		// We will simulate a 4% error rate
		final double ERROR_RATE = 0.04;
		double randomValue = Math.random();
		
		if(randomValue >= ERROR_RATE) {
			// get the price of the game
			// return game price
			return 0;
		}
		else throw new PaymentFailedException();
	}

}
