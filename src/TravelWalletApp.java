import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class TravelWalletApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final TravelWallet wallet = new TravelWallet(new SimpleCurrencyConverter());
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        while (true) {
            showMenu();
            int choice = getIntInput("Choose an option: ");

            if (choice == 5) {
                System.out.println("Exiting. Goodbye!");
                return;
            }

            boolean continueOperation = true;
            while (continueOperation) {
                switch (choice) {
                    case 1 -> addTrip();
                    case 2 -> addExpense();
                    case 3 -> viewTripSummary();
                    case 4 -> addSubTrip();
                    default -> {
                        System.out.println("Invalid option. Returning to main menu.");
                        continueOperation = false;
                        continue;
                    }
                }

                String response = getStringInput("Do you want to perform another operation in this category? (yes/no): ").toLowerCase();
                if (response.equals("no")) {
                    continueOperation = false;
                } else if (!response.equals("yes")) {
                    System.out.println("Invalid response. Returning to main menu.");
                    continueOperation = false;
                }
            }
        }
    }

    private static void addSubTrip() {
        String tripName = getStringInput("Enter the name of the trip to add a subtrip to: ");
        Trip trip = wallet.findTrip(tripName);

        if (trip == null) {
            System.out.println("Trip not found. Please enter a valid trip name.");
            return;
        }

        String subTripName = getStringInput("Enter subtrip name: ");
        int numberOfPeople = getIntInput("Enter number of people in subtrip: ");

        SubTrip subTrip = new SubTrip(subTripName, numberOfPeople);

        boolean addMoreExpenses = true;
        while (addMoreExpenses) {
            double expenseAmount = getDoubleInput("Enter expense amount for the subtrip: ");
            String currency = getStringInput("Enter currency (USD, EUR, GBP, VND): ").toUpperCase();
            ExpenseCategory category = getExpenseCategory();
            String description = getStringInput("Enter expense description: ");

            Expense expense = new Expense(expenseAmount, category, currency, description);
            subTrip.addExpense(expense);

            System.out.println("Expense added successfully.");

            String response = getStringInput("Do you want to add another expense to this subtrip? (yes/no): ").toLowerCase();
            if (!response.equals("yes")) {
                addMoreExpenses = false;
            }
        }

        trip.addSubTrip(subTrip);
        System.out.println("SubTrip added successfully.");
        System.out.println("Total expense per person for this subtrip: " + subTrip.calculateExpensePerPerson());
    }

    private static void showMenu() {
        System.out.println("\n--- Travel Wallet ---");
        System.out.println("1. Add Trip");
        System.out.println("2. Add Expense");
        System.out.println("3. View Trip Summary");
        System.out.println("4. Add SubTrip");
        System.out.println("5. Exit");
    }

    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("That's not a valid number. Please try again.");
            scanner.next();
        }
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static void viewTripSummary() {
        String tripName = getStringInput("Enter trip name: ");
        String currency = getStringInput("Enter currency for summary (USD, EUR, GBP, VND): ").toUpperCase();

        if (!currency.equals("USD") && !currency.equals("EUR") && !currency.equals("GBP") && !currency.equals("VND")) {
            System.out.println("Error: Invalid currency. Please use USD, EUR, GBP, or VND .");
            return;
        }

        wallet.printTripSummary(tripName, currency);
    }

    private static void addTrip() {
        String name;
        while (true) {
            name = getStringInput("Enter trip name: ").toUpperCase();
            if (wallet.findTrip(name) != null) {
                System.out.println("A trip with this name already exists. Please enter a different name.");
            } else {
                break;
            }
        }

        LocalDate startDate = getDateInput("Enter start date (yyyy-MM-dd): ");
        LocalDate endDate;
        while (true) {
            endDate = getDateInput("Enter end date (yyyy-MM-dd): ");
            if (endDate.isAfter(startDate)) {
                break;
            } else {
                System.out.println("End date must be after start date. Please try again.");
            }
        }

        double budget = getDoubleInput("Enter trip budget: ");
        Trip newTrip = new Trip(name, startDate, endDate, budget);
        wallet.addTrip(newTrip);
        System.out.println("Trip added successfully.");
    }

    private static void addExpense() {
        String tripName = getStringInput("Enter trip name: ");
        while (true) {
            double amount = getDoubleInput("Enter expense amount: ");
            String currency;
            while (true) {
                currency = getStringInput("Enter currency (USD, EUR, GBP, VND): ").toUpperCase();
                if (currency.equals("USD") || currency.equals("EUR") || currency.equals("GBP") || currency.equals("VND")) {
                    break;
                } else {
                    System.out.println("Invalid currency. Please enter one of USD, EUR, GBP, VND.");
                }
            }
            ExpenseCategory category = getExpenseCategory();
            String description = getStringInput("Enter description: ");

            Expense newExpense = new Expense(amount, category, currency, description);
            if (wallet.addExpense(tripName, newExpense)) {
                System.out.println("Expense added successfully.");
                break;
            } else {
                System.out.println("Failed to add expense. Please try again.");
            }
        }
    }


    private static LocalDate getDateInput(String prompt) {
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

    private static double getDoubleInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.println("That's not a valid number. Please try again.");
            scanner.next();
        }
        double result = scanner.nextDouble();
        scanner.nextLine();
        return result;
    }

    private static ExpenseCategory getExpenseCategory() {
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