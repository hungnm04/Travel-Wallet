package service;

import model.Expense;
import model.SubTrip;
import model.Trip;

import java.time.LocalDate;
import java.util.List;

public interface TripUseCase {
    Trip createTrip(String name, LocalDate startDate, LocalDate endDate, double budget);
    void addExpense(Trip trip, Expense expense);
    void addSubTrip(Trip trip, SubTrip subTrip);
    List<SubTrip> getSubTrips(Trip trip);
    String getName(Trip trip);
    LocalDate getStartDate(Trip trip);
    LocalDate getEndDate(Trip trip);
    List<Expense> getExpenses(Trip trip);
    double getBudget(Trip trip);
    double getTotal(Trip trip);
}
