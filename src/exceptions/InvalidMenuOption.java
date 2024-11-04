package exceptions;

/** Exception thrown when an invalid menu option is selected. */
public class InvalidMenuOption extends Exception {
    /**
     * Creates a new {@code InvalidMenuOption} with the specified message.
     * @param message the message explaining the error
     */
    public InvalidMenuOption(String message) {
        super(message);
    }
}
