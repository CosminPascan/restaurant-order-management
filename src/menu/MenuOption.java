package menu;

/** Represents a menu option that can execute a specific operation. */
public class MenuOption {
    /** The operation to be executed when this menu option is selected. */
    private Operation operation;

    /**
     * Creates a new {@code MenuOption} with the specified operation.
     * @param operation the operation associated with this menu option
     */
    public MenuOption(Operation operation) {
        this.operation = operation;
    }

    /** Executes the operation associated with this menu option. */
    public void executeTask() {
        operation.execute();
    }
}
