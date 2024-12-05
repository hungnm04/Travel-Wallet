package view;

import application.MenuMethods;
import utils.InputUtils;

public class MenuView {
    private final MenuMethods menuMethods;
    private final InputUtils inputUtils;

    public MenuView(MenuMethods menuMethods, InputUtils inputUtils) {
        this.menuMethods = menuMethods;
        this.inputUtils = inputUtils;
    }

    public void run() {
        while (true) {
            showMenu();
            int choice = inputUtils.getIntInput("Choose an option: ");
            switch (choice) {
                case 1 -> menuMethods.addTrip();
                case 2 -> menuMethods.addExpense();
                case 3 -> menuMethods.viewTripSummary();
                case 4 -> menuMethods.addSubTrip();
                case 5 -> {
                    System.out.println("Exiting. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void showMenu() {
        System.out.println("\n--- Travel Wallet ---");
        System.out.println("1. Add Trip");
        System.out.println("2. Add Expense");
        System.out.println("3. View Trip Summary");
        System.out.println("4. Add SubTrip");
        System.out.println("5. Exit");
        System.out.println("(Type 'menu' at any time to return to the main menu)");
    }
}