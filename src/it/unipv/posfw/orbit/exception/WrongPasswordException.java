package it.unipv.posfw.orbit.exception;

/**
 * exception thrown when a user attempts to log in with an incorrect password.
 */
public class WrongPasswordException extends Exception {
	
    private static final long serialVersionUID = -8967507200277475437L;
    
    // default constructor
    public WrongPasswordException() {
    }
    
    // constructor with message output
	public WrongPasswordException(String message) {
        super(message);
    }
}