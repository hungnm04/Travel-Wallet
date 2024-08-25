package service;

import model.Expense;
import model.SubTrip;
import model.Trip;

import java.time.LocalDate;
import java.util.List;

public class TripUseCaseImpl implements TripUseCase {
    @Override
    public Trip createTrip(String name, LocalDate startDate, LocalDate endDate, double budget) {
        return new Trip(name, startDate, endDate, budget);
    }

    @Override
    public void addExpense(Trip trip, Expense expense) {
        trip.addExpense(expense);
    }

    @Override
    public void addSubTrip(Trip trip, SubTrip subTrip) {
        trip.addSubTrip(subTrip);
    }

    @Override
    public List<SubTrip> getSubTrips(Trip trip) {
        return trip.getSubTrips();
    }

    @Override
    public String getName(Trip trip) {
        return trip.getName();
    }

    @Override
    public LocalDate getStartDate(Trip trip) {
        return trip.getStartDate();
    }

    @Override
    public LocalDate getEndDate(Trip trip) {
        return trip.getEndDate();
    }

    @Override
    public List<Expense> getExpenses(Trip trip) {
        return trip.getExpenses();
    }

    @Override
    public double getBudget(Trip trip) {
        return trip.getBudget();
    }

    @Override
    public double getTotal(Trip trip) {
        return trip.getTotal();
    }
}
