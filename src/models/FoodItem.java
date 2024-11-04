package models;

/**
 * Represents a food item that extends the {@link Product} class
 * and includes information about category and nutritional values.
 */
public class FoodItem extends Product {
    /** The category of the food item. */
    private Category category;
    /** The nutritional values of the food item (calories, proteins, carbohydrates, fats). */
    private double[] nutritionalValues;

    /**
     * Creates a new {@code FoodItem} with the specified name, price, category and nutritional values.
     * @param name              the name of the food item
     * @param price             the price of the food item
     * @param category          the category of the food item
     * @param nutritionalValues an array containing the nutritional values: calories, proteins, carbohydrates and fats
     */
    public FoodItem(String name, int price, Category category, double[] nutritionalValues) {
        super(name, price);
        this.category = category;
        this.nutritionalValues = nutritionalValues;
    }

    /**
     * Returns a string representation of the food item.
     * @return a string containing the name, price, category and nutritional values of the food item
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getName())
                .append("; Price: ").append(getPrice())
                .append("RON; models.Category: ").append(category)
                .append("; (calories, proteins, carbo, fats): (");
        for (double nutritionalValue : nutritionalValues) {
            sb.append(nutritionalValue).append(", ");
        }
        sb.replace(sb.length() - 2, sb.length(), ")");
        return sb.toString();
    }
}
