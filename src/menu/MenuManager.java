package menu;

import exceptions.InvalidOrderIndex;
import exceptions.InvalidProductIndex;
import file.TextFile;
import models.Order;
import models.Product;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import static utils.Constants.*;

/**
 * Manages the main menu operations, including reading input files, saving orders,
 * generating sales reports and handling menu options.
 */
public class MenuManager {
    /** The main menu displayed to the user. */
    private Menu menu;
    /** The text file handler for orders. */
    private TextFile ordersFile;
    /** The list of available products. */
    private List<Product> products;
    /** The list of current orders. */
    private List<Order> orders;

    /** Creates a new {@code MenuManager} and initializes the main menu. */
    public MenuManager() {
        menu = new Menu(MAIN_MENU_HEADER, MAIN_MENU_TEXT);
    }

    /** Runs the main menu loop. */
    public void run() {
        menu.run();
    }

    /** Reads the input files for the menu and orders. */
    public void readInputFiles() {
        TextFile menuFile = new TextFile("menu.txt");
        menuFile.open();
        products = menuFile.readMenu();

        ordersFile = new TextFile("orders.txt");
        ordersFile.open();
        orders = ordersFile.readOrders(products);
    }

    /** Saves the current list of orders to the orders file. */
    public void saveOrders() {
        ordersFile.saveOrders(orders);
    }

    /**
     * Generates a sales report mapping each date to the total sales value.
     * @return a map of dates to total sales values
     */
    public Map<LocalDate, Integer> generateSalesReport() {
        return orders.stream().collect(
                Collectors.groupingBy(Order::getDate, Collectors.summingInt(Order::getValue)));
    }

    /** Saves the sales report to a text file. */
    public void saveSalesReport() {
        TextFile salesReport = new TextFile("sales_report.txt");
        salesReport.open();
        Map<LocalDate, Integer> sales = generateSalesReport();
        salesReport.saveSalesReport(sales);
    }

    /**
     * Generates a list of best-selling products.
     * @return a map of product names to their sales counts
     */
    public Map<String, Long> generateBestSellers() {
        List<Product> allProducts = new ArrayList<>();
        for (Order o : orders) {
            allProducts.addAll(o.getProducts());
        }
        Map<String, Long> bs = allProducts.stream()
                .collect(Collectors.groupingBy(Product::getName, Collectors.counting()));
        Map<String, Long> bestSellers = bs.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(BEST_SELLERS_LIMIT)
                .collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new
                ));
        return bestSellers;
    }

    /**  Saves the bestsellers report to a text file. */
    public void saveBestSellers() {
        TextFile bestSellers = new TextFile("best_sellers.txt");
        bestSellers.open();
        Map<String, Long> bs = generateBestSellers();
        bestSellers.saveBestSellers(bs);
    }

    /**
     * Adds menu options to the main menu, including adding orders,
     * deleting orders, viewing sales reports, and viewing bestsellers.
     */
    public void addMenuOptions() {
        MenuOption addOrder = new MenuOption(() -> {
            System.out.println(ADD_ORDER_HEADER);
            products.forEach(p -> System.out.println((products.indexOf(p) + 1) + ". " + p));
            System.out.println(ADD_ORDER_FOOTER);

            Scanner scanner = new Scanner(System.in);
            Order order = new Order(LocalDate.now());
            while (true) {
                try {
                    int choice = scanner.nextInt();
                    if (choice == 0 && !order.getProducts().isEmpty()) {
                        orders.add(order);
                        break;
                    } else if (choice == 0) {
                        break;
                    } else if (choice < 0 || choice > products.size())
                        throw new InvalidProductIndex("Please enter a valid product index!");
                    Product product = products.get(--choice);
                    order.add(product);
                    System.out.println(product.getName() + " added");
                } catch(InvalidProductIndex e) {
                    System.out.println(e.getMessage());
                }
            }
            if (!order.getProducts().isEmpty()) System.out.println(order);
            System.out.println(LINE_BREAK);
        });

        MenuOption deleteOrder = new MenuOption(() -> {
            System.out.println(DELETE_ORDER_HEADER);
            Scanner scanner = new Scanner(System.in);
            while (true) {
                try {
                    orders.forEach(o -> System.out.println((orders.indexOf(o) + 1) + ". " + o));
                    System.out.println(DELETE_ORDER_FOOTER);
                    int choice = scanner.nextInt();
                    if (choice == 0) break;
                    else if (choice < 0 || choice > orders.size())
                        throw new InvalidOrderIndex("Please enter a valid order index!");
                    Order order = orders.get(--choice);
                    orders.remove(order);
                    System.out.println("models.Order with index " + (++choice) + " deleted");
                } catch (InvalidOrderIndex e) {
                    System.out.println(e.getMessage());
                }
                System.out.println(LINE_BREAK);
            }
        });

        MenuOption salesReport = new MenuOption(() -> {
            System.out.println(SALES_REPORT_HEADER);
            Map<LocalDate, Integer> sales = generateSalesReport();
            for (Map.Entry<LocalDate, Integer> entry : sales.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue() + "RON");
            }
            System.out.println(LINE_BREAK);
        });

        MenuOption bestSellers = new MenuOption(() -> {
            System.out.println(BEST_SELLERS_HEADER);
            Map<String, Long> bs = generateBestSellers();
            for (Map.Entry<String, Long> entry : bs.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
            System.out.println(LINE_BREAK);
        });

        menu.addOption(addOrder);
        menu.addOption(deleteOrder);
        menu.addOption(salesReport);
        menu.addOption(bestSellers);
    }
}
