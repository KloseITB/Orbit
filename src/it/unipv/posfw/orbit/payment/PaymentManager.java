package it.unipv.posfw.orbit.payment;

import it.unipv.posfw.orbit.exception.*;

public class PaymentManager {
	

	public static <E extends IPaymentMethod> double Pay(double amount, E paymentMethod) 
	throws AmountNotValidException {
		
		if( amount < 0) {
			throw new AmountNotValidException("invalid amount inserted");
		}
		
		try {
			if(paymentMethod.pay()) {
				return amount;
			}
		}
		catch (PaymentFailedException pfe) {
			//with UI: payment failed
		}
		
		// since the operation failed, say that the program needs to add 0 to the user's balance
		return 0;

	}
}
