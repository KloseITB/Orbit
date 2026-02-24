package it.unipv.posfw.orbit.exception;

/**
 * exception thrown when a user try to use gift card code that is not in the db.
 */
public class CodeNotFoundException extends Exception {
	
    private static final long serialVersionUID = 5651019359918314076L;

	// default constructor
    public CodeNotFoundException() {
        super("Gift card code not founf or invalid.");
    }

    // constructor with message output
    public CodeNotFoundException(String message) {
        super(message);
    }
}