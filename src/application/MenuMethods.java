package application;

import controller.TravelWalletController;
import model.Expense;
import model.ExpenseCategory;
import model.SubTrip;
import model.Trip;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class MenuMethods {

    private final TravelWalletController controller;
    private final Scanner scanner;

    public MenuMethods(TravelWalletController controller, Scanner scanner) {
        this.controller = controller;
        this.scanner = scanner;
    }

    public void addTrip() {
        String name = getStringInput("Enter trip name: ");
        LocalDate startDate = getDateInput("Enter start date (yyyy-MM-dd): ");
        LocalDate endDate = getDateInput("Enter end date (yyyy-MM-dd): ");
        double budget = getDoubleInput("Enter trip budget: ");

        Trip newTrip = controller.createTrip(name, startDate, endDate, budget);
        if (newTrip != null) {
            System.out.println("Trip added successfully.");
        } else {
            System.out.println("Failed to add trip. Please try again.");
        }
    }

    public void addExpense() {
        String tripName = getStringInput("Enter trip name: ");
        double amount = getDoubleInput("Enter expense amount: ");
        String currency = getStringInput("Enter currency (USD, EUR, GBP, VND): ").toUpperCase();
        ExpenseCategory category = getExpenseCategory();
        String description = getStringInput("Enter description: ");

        Expense newExpense = new Expense(amount, category, currency, description);
        if (controller.addExpense(tripName, newExpense)) {
            System.out.println("Expense added successfully.");
        } else {
            System.out.println("Failed to add expense. Please try again.");
        }
    }

    public void addSubTrip() {
        String tripName = getStringInput("Enter the name of the trip to add a subtrip to: ");
        String subTripName = getStringInput("Enter subtrip name: ");
        int numberOfPeople = getIntInput("Enter number of people in subtrip: ");

        SubTrip subTrip = new SubTrip(subTripName, numberOfPeople);

        if (controller.addSubTrip(tripName, subTrip)) {
            System.out.println("SubTrip added successfully.");
        } else {
            System.out.println("Failed to add SubTrip. Please try again.");
        }
    }

    public void viewTripSummary() {
        String tripName = getStringInput("Enter trip name: ");
        String currency = getStringInput("Enter currency for summary (USD, EUR, GBP, VND): ").toUpperCase();

        controller.printTripSummary(tripName, currency);
    }

    public int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("That's not a valid number. Please try again.");
            scanner.next();
        }
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

    public String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public LocalDate getDateInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String dateStr = scanner.nextLine();
            try {
                return LocalDate.parse(dateStr);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            }
        }
    }

    public double getDoubleInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.println("That's not a valid number. Please try again.");
            scanner.next();
        }
        double result = scanner.nextDouble();
        scanner.nextLine();
        return result;
    }

    private ExpenseCategory getExpenseCategory() {
        System.out.println("Select category:");
        for (ExpenseCategory category : ExpenseCategory.values()) {
            System.out.println(category.ordinal() + 1 + ". " + category);
        }
        int categoryChoice;
        while (true) {
            categoryChoice = getIntInput("Enter category number: ");
            if (categoryChoice > 0 && categoryChoice <= ExpenseCategory.values().length) {
                break;
            } else {
                System.out.println("Invalid category. Please select a valid category number.");
            }
        }
        return ExpenseCategory.values()[categoryChoice - 1];
    }
}
