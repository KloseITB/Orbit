package it.unipv.posfw.orbit.exception;

public class PaymentFailedException extends Exception{

	private static final long serialVersionUID = -1078872315198486894L;
	
	// Constructors
	public PaymentFailedException() {}
	
	public PaymentFailedException(String message) {
		super(message);
	}

}
