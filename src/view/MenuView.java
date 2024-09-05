package view;

import application.MenuMethods;

import java.util.Scanner;

public class MenuView {

    private final Scanner scanner;
    private final MenuMethods menuMethods;

    public MenuView(Scanner scanner, MenuMethods menuMethods) {
        this.scanner = scanner;
        this.menuMethods = menuMethods;
    }

    public void run() {
        while (true) {
            showMenu();
            int choice = getIntInput("Choose an option: ", 1, 5);

            if (choice == 5) {
                System.out.println("Exiting. Goodbye!");
                return;
            }

            performOperation(choice);
        }
    }

    private void showMenu() {
        System.out.println("\n--- Travel Wallet ---");
        System.out.println("1. Add Trip");
        System.out.println("2. Add Expense");
        System.out.println("3. View Trip Summary");
        System.out.println("4. Add SubTrip");
        System.out.println("5. Exit");
    }

    private void performOperation(int choice) {
        try {
            switch (choice) {
                case 1 -> menuMethods.addTrip();
                case 2 -> menuMethods.addExpense();
                case 3 -> menuMethods.viewTripSummary();
                case 4 -> menuMethods.addSubTrip();
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

        if (!continueOperation()) {
            return;
        }

        performOperation(choice);
    }

    private boolean continueOperation() {
        String response = getStringInput("Do you want to perform another operation in this category? (yes/no): ");
        return response.equalsIgnoreCase("yes");
    }

    private int getIntInput(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                int input = Integer.parseInt(scanner.nextLine().trim());
                if (input >= min && input <= max) {
                    return input;
                } else {
                    System.out.println("Please enter a number between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("That's not a valid number. Please try again.");
            }
        }
    }

    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}