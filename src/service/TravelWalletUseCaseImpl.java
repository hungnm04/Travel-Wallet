package service;

import model.Expense;
import model.SubTrip;
import model.Trip;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TravelWalletUseCaseImpl implements TravelWalletUseCase {
    private final List<Trip> trips = new ArrayList<>();
    private final CurrencyConverter currencyConverter;

    public TravelWalletUseCaseImpl(CurrencyConverter currencyConverter) {
        this.currencyConverter = currencyConverter;
    }

    @Override
    public Trip createTrip(String name, LocalDate startDate, LocalDate endDate, double budget) {
        Trip newTrip = new Trip(name, startDate, endDate, budget);
        trips.add(newTrip);
        return newTrip;
    }

    @Override
    public boolean addExpense(String tripName, Expense expense) {
        Trip trip = findTrip(tripName);
        if (trip != null) {
            trip.addExpense(expense);
            return true;
        }
        return false;
    }

    @Override
    public boolean addSubTrip(String tripName, SubTrip subTrip) {
        Trip trip = findTrip(tripName);
        if (trip != null) {
            trip.addSubTrip(subTrip);
            return true;
        }
        return false;
    }

    @Override
    public Trip findTrip(String tripName) {
        return trips.stream()
                .filter(trip -> trip.getName().equalsIgnoreCase(tripName))
                .findFirst()
                .orElse(null);
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

        System.out.println("Trip: " + trip.getName());
        System.out.println("Date: " + trip.getStartDate() + " to " + trip.getEndDate());

        double convertedBudget = currencyConverter.convert(trip.getBudget(), "VND", currency);
        System.out.println("Budget: " + String.format("%.2f", convertedBudget) + " " + currency);

        System.out.println("Expenses:");
        double totalSpent = printExpenses(trip.getExpenses(), currency, 0);

        for (SubTrip subTrip : trip.getSubTrips()) {
            System.out.println("SubTrip: " + subTrip.getName());
            double subTripTotal = printExpenses(subTrip.getExpenses(), currency, 2);
            totalSpent += subTripTotal;

            System.out.printf("  Total for SubTrip: %.2f %s%n", subTripTotal, currency);
            System.out.printf("  Expense per person: %.2f %s%n", subTripTotal / subTrip.getNumberOfPeople(), currency);
        }

        double remainingBudget = convertedBudget - totalSpent;

        System.out.printf("Total spent: %.2f %s%n", totalSpent, currency);
        System.out.printf("Remaining budget: %.2f %s%n", remainingBudget, currency);

        if (remainingBudget < 0) {
            System.out.println("Warning: You are over budget!");
        }
    }

    private double printExpenses(List<Expense> expenses, String targetCurrency, int indentation) {
        double total = 0;
        String indent = " ".repeat(indentation);
        for (Expense expense : expenses) {
            double convertedAmount = currencyConverter.convert(expense.amount(), expense.currency(), targetCurrency);
            total += convertedAmount;
            System.out.printf("%s- %s: %.2f %s%n", indent, expense.description(), convertedAmount, targetCurrency);
        }
        return total;
    }
}