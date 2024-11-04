package models;

/** An abstract representation of a product with a name and a price. */
public abstract class Product {
    /** The name of the product. */
    private String name;
    /** The price of the product in RON. */
    private int price;

    /**
     * Creates a new {@code Product} with the specified name and price.
     * @param name  the name of the product
     * @param price the price of the product in RON
     */
    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    /**
     * Returns the name of this product.
     * @return the product's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the price of this product.
     * @return the product's price
     */
    public int getPrice() {
        return price;
    }

    /**
     * Returns a string representation of the product.
     * @return a string describing the product
     */
    @Override
    public abstract String toString();
}
