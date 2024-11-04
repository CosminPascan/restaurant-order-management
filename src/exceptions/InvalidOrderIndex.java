package exceptions;

/** Exception thrown when an invalid order index is selected. */
public class InvalidOrderIndex extends Exception {
    /**
     * Creates a new {@code InvalidOrderIndex} with the specified message.
     * @param message the message explaining the error
     */
    public InvalidOrderIndex(String message) {
        super(message);
    }
}
