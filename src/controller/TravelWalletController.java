package controller;

import model.Expense;
import model.ExpenseCategory;
import model.Trip;
import service.TravelWalletUseCase;

import java.util.List;

public class TravelWalletController {
    private final TravelWalletUseCase travelWalletUseCase;

    public TravelWalletController(TravelWalletUseCase travelWalletUseCase) {
        this.travelWalletUseCase = travelWalletUseCase;
    }

    public void addTrip(Trip trip) {
        travelWalletUseCase.addTrip(trip);
    }

    public boolean addExpense(String tripName, Expense expense) {
        return travelWalletUseCase.addExpense(tripName, expense);
    }

    public double getTotalExpenses(String tripName, String targetCurrency) {
        return travelWalletUseCase.getTotalExpenses(tripName, targetCurrency);
    }

    public void printTripSummary(String tripName, String currency) {
        travelWalletUseCase.printTripSummary(tripName, currency);
    }

    public List<Expense> getExpensesByCategory(ExpenseCategory category) {
        return travelWalletUseCase.getExpensesByCategory(category);
    }

    public Trip findTrip(String tripName) {
        return travelWalletUseCase.findTrip(tripName);
    }


}
