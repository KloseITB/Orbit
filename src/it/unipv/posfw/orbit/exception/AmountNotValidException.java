package it.unipv.posfw.orbit.exception;

/**
 * exception thrown when a discount value is outside of the limits
 */
public class AmountNotValidException extends Exception{

	private static final long serialVersionUID = 339479837332951333L;
	
	// default constructor
	public AmountNotValidException(){}
	
	// constructor with message output
	public AmountNotValidException(String message){
		super(message);
	}
}
