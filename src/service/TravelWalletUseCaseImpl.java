package service;

import model.Expense;
import model.ExpenseCategory;
import model.SubTrip;
import model.Trip;

import java.util.ArrayList;
import java.util.List;

public class TravelWalletUseCaseImpl implements TravelWalletUseCase {
    private final List<Trip> trips;
    private final CurrencyConverter currencyConverter;

    public TravelWalletUseCaseImpl(CurrencyConverter currencyConverter) {
        this.trips = new ArrayList<>();
        this.currencyConverter = currencyConverter;
    }

    @Override
    public void addTrip(Trip trip) {
        trips.add(trip);
    }

    @Override
    public boolean addExpense(String tripName, Expense expense) {
        Trip trip = findTrip(tripName);
        if (trip == null) {
            System.out.println("Trip not found.");
            return false;
        }
        if (trip.getTotal() + expense.amount() > trip.getBudget()) {
            System.out.println("Warning: This expense exceeds the budget. Please re-enter.");
            return false;
        }
        trip.addExpense(expense);
        return true;
    }

    @Override
    public Trip findTrip(String tripName) {
        for (Trip trip : trips) {
            if (trip.getName().equals(tripName)) {
                return trip;
            }
        }
        return null;
    }

    @Override
    public double getTotalExpenses(String tripName, String targetCurrency) {
        Trip trip = findTrip(tripName);
        if (trip == null) {
            throw new IllegalArgumentException("Trip not found: " + tripName);
        }

        double total = 0;
        for (Expense expense : trip.getExpenses()) {
            double convertedAmount = currencyConverter.convert(expense.amount(), expense.currency(), targetCurrency);
            total += convertedAmount;
        }

        return total;
    }

    @Override
    public void printTripSummary(String tripName, String currency) {
        Trip trip = findTrip(tripName);
        if (trip == null) {
            System.out.println("Error: Trip not found: " + tripName);
            return;
        }

        System.out.println("Trip: " + trip.getName());
        System.out.println("Date: " + trip.getStartDate() + " to " + trip.getEndDate());

        double convertedBudget = currencyConverter.convert(trip.getBudget(), "VND", currency);
        System.out.println("Budget: " + String.format("%.2f", convertedBudget) + " " + currency);

        System.out.println("Expenses:");

        double totalSpent = 0;
        for (Expense expense : trip.getExpenses()) {
            double convertedAmount = currencyConverter.convert(expense.amount(), expense.currency(), currency);
            totalSpent += convertedAmount;
            System.out.printf("- %s: %.2f %s%n", expense.description(), convertedAmount, currency);
        }

        for (SubTrip subTrip : trip.getSubTrips()) {
            System.out.println("SubTrip: " + subTrip.getName());
            double subTripTotal = 0;

            for (Expense expense : subTrip.getExpenses()) {
                double convertedAmount = currencyConverter.convert(expense.amount(), expense.currency(), currency);
                subTripTotal += convertedAmount;
                totalSpent += convertedAmount;
                System.out.printf("  - %s: %.2f %s%n", expense.description(), convertedAmount, currency);
            }

            int numberOfPeople = subTrip.getNumberOfPeople();
            double expensePerPerson = subTripTotal / numberOfPeople;

            System.out.printf("  Total for SubTrip: %.2f %s%n", subTripTotal, currency);
            System.out.printf("  Expense per person: %.2f %s%n", expensePerPerson, currency);
        }


        double remainingBudget = convertedBudget - totalSpent;

        System.out.printf("Total spent: %.2f %s%n", totalSpent, currency);
        System.out.printf("Remaining budget: %.2f %s%n", remainingBudget, currency);

        if (remainingBudget < 0) {
            System.out.println("Warning: You are over budget!");
        }
    }

    @Override
    public List<Expense> getExpensesByCategory(ExpenseCategory category) {
        List<Expense> categoryExpenses = new ArrayList<>();
        for (Trip trip : trips) {
            for (Expense expense : trip.getExpenses()) {
                if (expense.category() == category) {
                    categoryExpenses.add(expense);
                }
            }
        }
        return categoryExpenses;
    }
}
