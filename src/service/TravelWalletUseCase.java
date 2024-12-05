package service;

import java.time.LocalDate;
import java.util.List;
import model.Expense;
import model.SubTrip;
import model.Trip;

public interface TravelWalletUseCase {
    Trip createTrip(String name, LocalDate startDate, LocalDate endDate, double budget);
    boolean addExpense(String tripName, Expense expense);
    boolean addSubTrip(String tripName, SubTrip subTrip);
    Trip findTrip(String tripName);
    List<SubTrip> getSubTrips(String tripName);
    List<Expense> getTripExpenses(String tripName);
    double getTripBudget(String tripName);
    double getTripTotal(String tripName);
    void printTripSummary(String tripName, String currency);
}