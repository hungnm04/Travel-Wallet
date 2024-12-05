package service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Expense;
import model.SubTrip;
import model.Trip;

public class TravelWalletUseCaseImpl implements TravelWalletUseCase {
    private final Map<String, Trip> trips = new HashMap<>();
    private final CurrencyConverter currencyConverter;

    public TravelWalletUseCaseImpl(CurrencyConverter currencyConverter) {
        this.currencyConverter = currencyConverter;
    }

    @Override
    public Trip createTrip(String name, LocalDate startDate, LocalDate endDate, double budget) {
        String key = name.toLowerCase();
        if (trips.containsKey(key)) {
            throw new IllegalArgumentException("Trip already exists with name: " + name);
        }
        Trip newTrip = new Trip(name, startDate, endDate, budget);
        trips.put(key, newTrip);
        return newTrip;
    }

    @Override
    public boolean addExpense(String tripName, Expense expense) {
        Trip trip = findTrip(tripName);
        if (trip != null) {
            trip.addExpense(expense);
            return true;
        }
        throw new IllegalArgumentException("Trip not found: " + tripName);
    }

    @Override
    public boolean addSubTrip(String tripName, SubTrip subTrip) {
        Trip trip = findTrip(tripName);
        if (trip != null) {
            trip.addSubTrip(subTrip);
            return true;
        }
        throw new IllegalArgumentException("Trip not found: " + tripName);
    }

    @Override
    public Trip findTrip(String tripName) {
        return trips.get(tripName.toLowerCase());
    }

    @Override
    public List<SubTrip> getSubTrips(String tripName) {
        Trip trip = findTrip(tripName);
        return trip != null ? trip.getSubTrips() : new ArrayList<>();
    }

    @Override
    public List<Expense> getTripExpenses(String tripName) {
        Trip trip = findTrip(tripName);
        return trip != null ? trip.getExpenses() : new ArrayList<>();
    }

    @Override
    public double getTripBudget(String tripName) {
        Trip trip = findTrip(tripName);
        return trip != null ? trip.getBudget() : 0.0;
    }

    @Override
    public double getTripTotal(String tripName) {
        Trip trip = findTrip(tripName);
        return trip != null ? trip.getTotal() : 0.0;
    }

    @Override
    public void printTripSummary(String tripName, String currency) {
        Trip trip = findTrip(tripName);
        if (trip == null) {
            System.out.println("Trip not found: " + tripName);
            return;
        }

        System.out.println("\nTrip: " + trip.getName());
        System.out.println("Date: " + trip.getStartDate() + " to " + trip.getEndDate());

        double convertedBudget = currencyConverter.convert(trip.getBudget(), "VND", currency);
        System.out.printf("Budget: %.2f %s%n", convertedBudget, currency);

        System.out.println("\nExpenses:");
        double totalSpent = printExpenses(trip.getExpenses(), currency, 0);

        for (SubTrip subTrip : trip.getSubTrips()) {
            System.out.println("\nSubTrip: " + subTrip.getName());
            double subTripTotal = printExpenses(subTrip.getExpenses(), currency, 2);
            totalSpent += subTripTotal;

            System.out.printf("  Total for SubTrip: %.2f %s%n", subTripTotal, currency);
            System.out.printf("  Expense per person: %.2f %s%n", subTripTotal / subTrip.getNumberOfPeople(), currency);
        }

        double remainingBudget = convertedBudget - totalSpent;

        System.out.printf("\nTotal spent: %.2f %s%n", totalSpent, currency);
        System.out.printf("Remaining budget: %.2f %s%n", remainingBudget, currency);

        if (remainingBudget < 0) {
            System.out.println("Warning: You are over budget!");
        }
    }

    private double printExpenses(List<Expense> expenses, String targetCurrency, int indentation) {
        double total = 0;
        String indent = " ".repeat(indentation);
        for (Expense expense : expenses) {
            double convertedAmount = currencyConverter.convert(expense.getAmount(), expense.getCurrency(), targetCurrency);
            total += convertedAmount;
            System.out.printf("%s- %s: %.2f %s%n", indent, expense.getDescription(), convertedAmount, targetCurrency);
        }
        return total;
    }
}