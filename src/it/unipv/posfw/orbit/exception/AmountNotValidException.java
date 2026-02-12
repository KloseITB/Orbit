package it.unipv.posfw.orbit.exception;

public class AmountNotValidException extends Exception{

	private static final long serialVersionUID = 339479837332951333L;
	
	// default constructor
	public AmountNotValidException(){}
	
	// constructor with message output
	public AmountNotValidException(String message){
		super(message);
	}
}
