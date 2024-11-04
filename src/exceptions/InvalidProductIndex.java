package exceptions;

/** Exception thrown when an invalid product index is selected. */
public class InvalidProductIndex extends Exception {
    /**
     * Creates a new {@code InvalidProductIndex} with the specified message.
     * @param message the message explaining the error
     */
    public InvalidProductIndex(String message) {
        super(message);
    }
}
