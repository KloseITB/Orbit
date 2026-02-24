package it.unipv.posfw.orbit.exception;

/**
 * exception thrown when a game purchase transaction fails due to errors or insufficient funds.
 */
public class PaymentFailedException extends Exception{

	private static final long serialVersionUID = -1078872315198486894L;
	
	// default constructor
	public PaymentFailedException() {}
	
	// constructor with message output
	public PaymentFailedException(String message) {
		super(message);
	}

}
