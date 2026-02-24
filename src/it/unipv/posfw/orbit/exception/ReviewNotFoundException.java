package it.unipv.posfw.orbit.exception;

/**
 * exception thrown when a user's review for a specific game cannot be found in the database.
 */
public class ReviewNotFoundException extends Exception{

	private static final long serialVersionUID = 3402372634732400196L;

	// default constructor
	public ReviewNotFoundException() {}
	
	// constructor with message output
	public ReviewNotFoundException(String message) {
		super(message);
	}

}
