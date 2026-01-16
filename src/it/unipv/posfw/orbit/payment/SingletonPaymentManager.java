package it.unipv.posfw.orbit.payment;

import it.unipv.posfw.orbit.exceptions.*;

public class SingletonPaymentManager {
	
	private static SingletonPaymentManager uniqueInstance;
	
	// Constructor
	
	private SingletonPaymentManager() {}
	
	
	// Get instance of the class
	public static SingletonPaymentManager getInstance() {
		if (uniqueInstance == null ) {
			return uniqueInstance = new SingletonPaymentManager();
		} else return uniqueInstance;
	}
	
	public double Pay(double amount, PaymentOptions payMethod) 
	throws PaymentMethodNotValidException, AmountNotValidException {
		
		if( amount < 0) {
			throw new AmountNotValidException("invalid amount inserted");
		}
		
		switch (payMethod) {
			case PaymentOptions.DEBIT:
				// code
				break;
			case PaymentOptions.GIFT:
				// code
				break;
			case PaymentOptions.PAYLAD:
				// code
				break;
			default:
				throw new PaymentMethodNotValidException();
		}
		return amount;
	}
}
