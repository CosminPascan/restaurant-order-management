package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/** Represents an order containing a list of products, the date of the order and the total value. */
public class Order {
    /** The list of products in the order. */
    private List<Product> products = new ArrayList<>();
    /** The date when the order was placed. */
    private LocalDate date;
    /** The total value of the order. */
    private int value;

    /**
     * Constructs a new {@code Order} with the specified date.
     * @param date the date of the order
     */
    public Order(LocalDate date) {
        this.date = date;
    }

    /**
     * Returns the date of the order.
     * @return the order date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Returns the total value of the order.
     * @return the total value
     */
    public int getValue() {
        return value;
    }

    /**
     * Returns the list of products in the order.
     * @return the list of products
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Adds a product to the order and updates the total value.
     * @param product the product to add
     */
    public void add(Product product) {
        products.add(product);
        value += product.getPrice();
    }

    /**
     * Returns a string representation of the order.
     * @return a string containing product names, total value and date of the order
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Product m : products) {
            sb.append(m.getName()).append(" ");
        }
        sb.replace(sb.length() - 1, sb.length(), "; ");
        sb.append("Value: ").append(value);
        sb.append("RON; Date: ").append(date.toString());
        return sb.toString();
    }
}
