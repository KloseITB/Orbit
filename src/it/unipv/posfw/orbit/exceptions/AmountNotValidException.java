package it.unipv.posfw.orbit.exceptions;

public class AmountNotValidException extends Exception{

	private static final long serialVersionUID = 339479837332951333L;
	
	// Constructors
	
	public AmountNotValidException(){}
	
	public AmountNotValidException(String message){
		super(message);
	}
}
