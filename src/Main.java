import menu.MenuManager;

public class Main {
    public static void main(String[] args) {
        MenuManager manager = new MenuManager();

        manager.readInputFiles();

        manager.addMenuOptions();

        manager.run();

        manager.saveOrders();

        manager.saveSalesReport();

        manager.saveBestSellers();
    }
}