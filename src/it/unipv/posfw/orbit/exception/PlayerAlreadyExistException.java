package it.unipv.posfw.orbit.exception;

public class PlayerAlreadyExistException extends Exception {

    private static final long serialVersionUID = -4402020956900902280L;
    
    // default constructor
    public PlayerAlreadyExistException() {
    }
    
    // constructor with message output
	public PlayerAlreadyExistException(String message) {
        super(message);
    }
}