package application;

import controller.TravelWalletController;
import java.time.LocalDate;
import model.Expense;
import model.ExpenseCategory;
import model.SubTrip;
import utils.InputUtils;
import utils.MenuException;

public class MenuMethods {
    private final TravelWalletController controller;
    private final InputUtils inputUtils;

    public MenuMethods(TravelWalletController controller, InputUtils inputUtils) {
        this.controller = controller;
        this.inputUtils = inputUtils;
    }

    public void addTrip() {
        try {
            String name = inputUtils.getStringInput("Enter trip name");
            LocalDate startDate = inputUtils.getDateInput("Enter start date (yyyy-MM-dd)");
            LocalDate endDate;

            while (true) {
                endDate = inputUtils.getDateInput("Enter end date (yyyy-MM-dd)");
                if (endDate.isAfter(startDate)) {
                    break;
                } else if (endDate.isEqual(startDate)) {
                    System.out.println("End date must be different from the start date. Please try again.");
                } else {
                    System.out.println("End date must be after the start date. Please try again.");
                }
            }

            double budget = inputUtils.getDoubleInput("Enter trip budget");

            try {
                controller.createTrip(name, startDate, endDate, budget);
                System.out.println("Trip added successfully.");
            } catch (IllegalArgumentException e) {
                System.out.println("Failed to add trip: " + e.getMessage());
            }
        } catch (MenuException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addExpense() {
        try {
            String tripName = inputUtils.getStringInput("Enter trip name");
            double amount = inputUtils.getDoubleInput("Enter expense amount");
            String currency = inputUtils.getStringInput("Enter currency (USD, EUR, GBP, VND)").toUpperCase();
            ExpenseCategory category = getExpenseCategory();
            String description = inputUtils.getStringInput("Enter description");

            try {
                Expense newExpense = new Expense(amount, category, currency, description);
                controller.addExpense(tripName, newExpense);
                System.out.println("Expense added successfully.");
            } catch (IllegalArgumentException e) {
                System.out.println("Failed to add expense: " + e.getMessage());
            }
        } catch (MenuException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addSubTrip() {
        try {
            String tripName = inputUtils.getStringInput("Enter the name of the trip to add a subtrip to");
            String subTripName = inputUtils.getStringInput("Enter subtrip name");
            int numberOfPeople = inputUtils.getIntInput("Enter number of people in subtrip");

            SubTrip subTrip = new SubTrip(subTripName, numberOfPeople);

            try {
                controller.addSubTrip(tripName, subTrip);
                System.out.println("SubTrip added successfully.");
            } catch (IllegalArgumentException e) {
                System.out.println("Failed to add SubTrip: " + e.getMessage());
            }
        } catch (MenuException e) {
            System.out.println(e.getMessage());
        }
    }

    public void viewTripSummary() {
        try {
            String tripName = inputUtils.getStringInput("Enter trip name");
            String currency = inputUtils.getStringInput("Enter currency for summary (USD, EUR, GBP, VND)").toUpperCase();

            controller.printTripSummary(tripName, currency);
        } catch (MenuException e) {
            System.out.println(e.getMessage());
        }
    }

    private ExpenseCategory getExpenseCategory() {
        System.out.println("Select category:");
        ExpenseCategory[] categories = ExpenseCategory.values();
        for (int i = 0; i < categories.length; i++) {
            System.out.println((i + 1) + ". " + categories[i]);
        }

        int choice;
        while (true) {
            choice = inputUtils.getIntInput("Enter category number");
            if (choice >= 1 && choice <= categories.length) {
                break;
            } else {
                System.out.println("Invalid category. Please try again.");
            }
        }
        return categories[choice - 1];
    }
}