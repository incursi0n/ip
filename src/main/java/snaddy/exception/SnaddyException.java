package snaddy.exception;

/**
 * Represents an exception specific to the Snaddy application.
 * Used to handle application-specific errors and provide user-friendly error messages.
 */
public class SnaddyException extends Exception {
    /**
     * Constructs a SnaddyException with the specified error message.
     *
     * @param message The error message to be displayed to the user.
     */
    public SnaddyException(String message) {
        super(message);
    }
}
