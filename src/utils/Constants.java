package utils;

/** A utility class that holds constant values used throughout the application. */
public class Constants {
    private Constants() {}

    public static final String LINE_BREAK = "--------------------------------------------------";

    public static final String MAIN_MENU_HEADER = "Enter a choice...";
    public static final String[] MAIN_MENU_TEXT = new String[] {
            "Add order",
            "Delete order",
            "Sales Report",
            "Best sellers",
            "Press 0 to exit"
    };

    public static final String ADD_ORDER_HEADER = "Add products to the order...";
    public static final String ADD_ORDER_FOOTER = "Press 0 to finish order...";

    public static final String DELETE_ORDER_HEADER = "Choose an order to delete...";
    public static final String DELETE_ORDER_FOOTER = "Press 0 to finish deleting orders...";

    public static final String SALES_REPORT_HEADER = "Sales Report";

    public static final int BEST_SELLERS_LIMIT = 5;
    public static final String BEST_SELLERS_HEADER = "Best Sellers (Top " + BEST_SELLERS_LIMIT + ")";
}
