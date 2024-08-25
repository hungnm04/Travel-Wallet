package view;

import service.SimpleCurrencyConverter;
import service.TravelWalletUseCase;
import service.TravelWalletUseCaseImpl;

import java.util.Scanner;

public class MenuView {

    private final Scanner scanner = new Scanner(System.in);
    private final TravelWalletUseCase useCase = new TravelWalletUseCaseImpl(new SimpleCurrencyConverter());

    private final MenuMethods menuMethods = new MenuMethods(useCase);

    public void run() {
        while (true) {
            showMenu();
            int choice = menuMethods.getIntInput("Choose an option: ");

            if (choice == 5) {
                System.out.println("Exiting. Goodbye!");
                return;
            }

            boolean continueOperation = true;
            while (continueOperation) {
                switch (choice) {
                    case 1 -> menuMethods.addTrip();
                    case 2 -> menuMethods.addExpense();
                    case 3 -> menuMethods.viewTripSummary();
                    case 4 -> menuMethods.addSubTrip();
                    default -> {
                        System.out.println("Invalid option. Returning to main menu.");
                        continueOperation = false;
                        continue;
                    }
                }

                String response = menuMethods.getStringInput("Do you want to perform another operation in this category? (yes/no): ").toLowerCase();
                if (response.equals("no")) {
                    continueOperation = false;
                } else if (!response.equals("yes")) {
                    System.out.println("Invalid response. Returning to main menu.");
                    continueOperation = false;
                }
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
    }
}
