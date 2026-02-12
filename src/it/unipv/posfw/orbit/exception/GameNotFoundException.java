package it.unipv.posfw.orbit.exception;

public class GameNotFoundException extends Exception{
	
	private static final long serialVersionUID = 2565138660652519756L;
	
	// default constructor
	public GameNotFoundException() {
	}
	
	// constructor with message output
	public GameNotFoundException(String message) {
		super(message);
	}

}
