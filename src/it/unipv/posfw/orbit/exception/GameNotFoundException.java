package it.unipv.posfw.orbit.exception;

public class GameNotFoundException extends Exception{
	
	private static final long serialVersionUID = 2565138660652519756L;
	
	// Constructors
	public GameNotFoundException() {
	}
	
	public GameNotFoundException(String message) {
		super(message);
	}

}
