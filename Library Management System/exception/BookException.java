package system.exception;

public class BookException extends Exception {
    
	private static final long serialVersionUID = 1L;

	// Constructor that takes an error message as a parameter
    public BookException(String errorMessage) {
        // Print the error message to the standard error output
        System.err.println("BookException: " + errorMessage);
    }
}