package it.unipv.posfw.orbit.exception;

public class WrongPasswordException extends Exception {
	
    private static final long serialVersionUID = -8967507200277475437L;

	public WrongPasswordException(String message) {
        super(message);
    }
}