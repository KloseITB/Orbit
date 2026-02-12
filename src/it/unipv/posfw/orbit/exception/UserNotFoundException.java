package it.unipv.posfw.orbit.exception;

public class UserNotFoundException extends Exception {
	
    private static final long serialVersionUID = 7761418083095757772L;
    
    // default constructor
	public UserNotFoundException() {
    }
    
    // constructor with message output
	public UserNotFoundException(String message) {
        super(message);
    }
}