package it.unipv.posfw.orbit.exception;

public class ReviewNotFoundException extends Exception{

	private static final long serialVersionUID = 3402372634732400196L;

	// default constructor
	public ReviewNotFoundException() {}
	
	// constructor with message output
	public ReviewNotFoundException(String message) {
		super(message);
	}

}
