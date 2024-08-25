package service;

import model.Expense;
import model.ExpenseCategory;
import model.Trip;

import java.util.List;

public interface TravelWalletUseCase {
    void addTrip(Trip trip);
    Trip findTrip(String tripName);
    boolean addExpense(String tripName, Expense expense);
    double getTotalExpenses(String tripName, String targetCurrency);
    void printTripSummary(String tripName, String currency);
    List<Expense> getExpensesByCategory(ExpenseCategory category);
}
