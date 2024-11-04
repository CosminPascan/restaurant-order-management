package models;

/**
 * Represents a drink item that extends the {@link Product} class
 * and includes information about alcohol content and available sizes.
 */
public class DrinkItem extends Product {
    /** Indicates whether the drink is alcohol-free. */
    private boolean isAlcoholFree;
    /** The available sizes of the drink in milliliters (e.g. 250, 500, 1000). */
    private int[] availableSize;

    /**
     * Constructs a new {@code DrinkItem} with the specified name, price, alcohol content and available sizes.
     * @param name           the name of the drink
     * @param price          the price of the drink
     * @param isAlcoholFree  {@code true} if the drink is alcohol-free; {@code false} otherwise
     * @param availableSize  an array of available sizes in milliliters
     */
    public DrinkItem(String name, int price, boolean isAlcoholFree, int[] availableSize) {
        super(name, price);
        this.isAlcoholFree = isAlcoholFree;
        this.availableSize = availableSize;
    }

    /**
     * Returns a string representation of the drink item.
     * @return a string containing the name, price, alcohol content and available sizes of the drink
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getName())
                .append("; Price: ").append(getPrice())
                .append("RON; Alcohol Free: ").append(isAlcoholFree)
                .append("; Available Size: ");
        for (int s : availableSize) {
            sb.append(s).append("ml, ");
        }
        sb.replace(sb.length() - 2, sb.length(), "");
        return sb.toString();
    }
}
