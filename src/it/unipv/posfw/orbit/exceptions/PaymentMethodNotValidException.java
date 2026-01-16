package it.unipv.posfw.orbit.exceptions;

public class PaymentMethodNotValidException extends Exception{

	private static final long serialVersionUID = -1078872315198486894L;
	
	// Constructors
	public PaymentMethodNotValidException() {}
	
	public PaymentMethodNotValidException(String message) {
		super(message);
	}

}
