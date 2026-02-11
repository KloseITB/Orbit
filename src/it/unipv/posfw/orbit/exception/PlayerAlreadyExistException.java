package it.unipv.posfw.orbit.exception;

public class PlayerAlreadyExistException extends Exception {

    private static final long serialVersionUID = -4402020956900902280L;

	public PlayerAlreadyExistException(String message) {
        super(message);
    }
}