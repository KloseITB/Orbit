package it.unipv.posfw.orbit.exception;

/**
 * exception thrown during the signup process when a user attempts to register with a nickname that is already taken.
 */
public class UserAlreadyExistException extends Exception {

    private static final long serialVersionUID = -4402020956900902280L;
    
    // default constructor
    public UserAlreadyExistException() {
    }
    
    // constructor with message output
	public UserAlreadyExistException(String message) {
        super(message);
    }
}