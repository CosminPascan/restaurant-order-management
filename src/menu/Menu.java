package menu;

import exceptions.InvalidMenuOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static utils.Constants.*;

/** Represents a menu with options that can be selected and executed by the user. */
public class Menu {
    /** The header text displayed at the top of the menu. */
    private String header;
    /** An array of text options displayed to the user. */
    private String[] text;
    /** A list of menu options available in the menu. */
    private List<MenuOption> options = new ArrayList<>();

    /**
     * Creates a new {@code Menu} with the specified header and text options.
     * @param header the header text of the menu
     * @param text   the array of text options for the menu
     */
    public Menu(String header, String[] text) {
        this.header = header;
        this.text = text;
    }

    /**
     * Adds a menu option to the list of available options.
     * @param option the {@link MenuOption} to add
     */
    public void addOption(MenuOption option) {
        options.add(option);
    }

    /** Displays the menu header and text options to the user. */
    public void displayText() {
        System.out.println(header);
        for (int i = 0; i < text.length - 1; i++) {
            System.out.println((i + 1) + ". " + text[i]);
        }
        System.out.println(text[text.length - 1]);
    }

    /** Runs the menu, allowing the user to select options and execute tasks. */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                displayText();
                int choice = scanner.nextInt();
                if (choice < 0 || choice > options.size())
                    throw new InvalidMenuOption("Please enter a valid menu option!");
                if (choice == 0) break;
                System.out.println(LINE_BREAK);
                if (options != null) {
                    options.get(--choice).executeTask();
                }
            } catch (InvalidMenuOption e) {
                System.out.println(e.getMessage());
                System.out.println(LINE_BREAK);
            }
        }
    }
}
