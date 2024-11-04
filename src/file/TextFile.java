package file;

import models.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static utils.Constants.BEST_SELLERS_HEADER;
import static utils.Constants.SALES_REPORT_HEADER;

/** Represents a text file handler for reading from and writing to text files. */
public class TextFile {
    /** The name of the text file. */
    private String name;
    /** The {@link File} object associated with the text file. */
    private File file;

    /**
     * Creates a new {@code TextFile} with the specified file name.
     * @param name the name of the text file
     */
    public TextFile(String name) {
        this.name = name;
    }

    /** Opens the file with the specified name. */
    public void open() {
        file = new File(name);
    }

    /**
     * Reads a {@link FoodItem} from the given array of words.
     * @param words an array of strings containing the food item data
     * @return a new {@link FoodItem} object
     */
    public FoodItem readFoodItem(String[] words) {
        String name = words[1];
        int price = Integer.parseInt(words[2]);
        Category category = Category.valueOf(words[3].toUpperCase());
        String[] nvString = words[4].split(" ");
        double[] nutritionalValues = new double[nvString.length];
        for (int i = 0; i < nvString.length; i++) {
            nutritionalValues[i] = Double.parseDouble(nvString[i]);
        }
        return new FoodItem(name, price, category, nutritionalValues);
    }

    /**
     * Reads a {@link DrinkItem} from the given array of words.
     * @param words an array of strings containing the drink item data
     * @return a new {@link DrinkItem} object
     */
    public DrinkItem readDrinkItem(String[] words) {
        String name = words[1];
        int price = Integer.parseInt(words[2]);
        boolean isAlcoholFree = Boolean.parseBoolean(words[3]);
        String[] asString = words[4].split(" ");
        int[] availableSize = new int[asString.length];
        for (int i = 0; i < asString.length; i++) {
            availableSize[i] = Integer.parseInt(asString[i]);
        }
        return new DrinkItem(name, price, isAlcoholFree, availableSize);
    }

    /**
     * Reads the menu items from the text file and returns a list of products.
     * @return a list of {@link Product} objects read from the file
     */
    public List<Product> readMenu() {
        List<Product> products = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            String line = br.readLine();
            while (line != null) {
                String[] words = line.split(",");
                Product p;
                if (words[0].equals("food")) {
                    p = readFoodItem(words);
                } else {
                    p = readDrinkItem(words);
                }
                products.add(p);
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    /**
     * Reads the orders from the text file and returns a list of orders.
     * @param products the list of products to associate with the orders
     * @return a list of {@link Order} objects read from the file
     */
    public List<Order> readOrders(List<Product> products) {
        List<Order> orders = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            String line = br.readLine();
            while (line != null) {
                String[] words = line.split(",");
                String[] names = words[0].split(" ");
                LocalDate date = LocalDate.parse(words[1]);
                Order order = new Order(date);
                for (String name : names) {
                    Product product = products.stream().filter(p ->
                            p.getName().equals(name)).findFirst().orElse(null);
                    order.add(product);
                }
                orders.add(order);
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }

    /**
     * Saves the given list of orders to the text file.
     * @param orders the list of {@link Order} objects to save
     */
    public void saveOrders(List<Order> orders) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);
            for (Order o : orders) {
                StringBuilder sb = new StringBuilder();
                for (Product p : o.getProducts()) {
                    sb.append(p.getName()).append(" ");
                }
                sb.replace(sb.length() - 1, sb.length(), ",");
                sb.append(o.getDate().toString());
                bw.write(sb.toString());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Saves the sales report to the text file.
     * @param sales a map of dates to sales amounts
     */
    public void saveSalesReport(Map<LocalDate, Integer> sales) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(SALES_REPORT_HEADER);
            bw.newLine();
            for (Map.Entry<LocalDate, Integer> entry : sales.entrySet()) {
                bw.write(entry.getKey() + ": " + entry.getValue() + "RON");
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Saves the bestsellers report to the text file.
     * @param bestSellers a map of product names to sales counts
     */
    public void saveBestSellers(Map<String, Long> bestSellers) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(BEST_SELLERS_HEADER);
            bw.newLine();
            for (Map.Entry<String, Long> entry : bestSellers.entrySet()) {
                bw.write(entry.getKey() + ": " + entry.getValue());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
