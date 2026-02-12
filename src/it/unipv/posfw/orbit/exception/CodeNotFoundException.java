package it.unipv.posfw.orbit.exception;

public class CodeNotFoundException extends Exception {
	
    private static final long serialVersionUID = 5651019359918314076L;

	// default constructor
    public CodeNotFoundException() {
        super("Codice Gift Card non trovato o non valido.");
    }

    // constructor with message output
    public CodeNotFoundException(String message) {
        super(message);
    }
}