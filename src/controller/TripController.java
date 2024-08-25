package controller;

import model.Expense;
import model.SubTrip;
import model.Trip;
import service.TripUseCase;

import java.time.LocalDate;
import java.util.List;

public class TripController {
    private final TripUseCase tripUseCase;

    public TripController(TripUseCase tripUseCase) {
        this.tripUseCase = tripUseCase;
    }

    public Trip createTrip(String name, LocalDate startDate, LocalDate endDate, double budget) {
        return tripUseCase.createTrip(name, startDate, endDate, budget);
    }

    public void addExpense(Trip trip, Expense expense) {
        tripUseCase.addExpense(trip, expense);
    }

    public void addSubTrip(Trip trip, SubTrip subTrip) {
        tripUseCase.addSubTrip(trip, subTrip);
    }

    public List<SubTrip> getSubTrips(Trip trip) {
        return tripUseCase.getSubTrips(trip);
    }

    public String getName(Trip trip) {
        return tripUseCase.getName(trip);
    }

    public LocalDate getStartDate(Trip trip) {
        return tripUseCase.getStartDate(trip);
    }

    public LocalDate getEndDate(Trip trip) {
        return tripUseCase.getEndDate(trip);
    }

    public List<Expense> getExpenses(Trip trip) {
        return tripUseCase.getExpenses(trip);
    }

    public double getBudget(Trip trip) {
        return tripUseCase.getBudget(trip);
    }

    public double getTotal(Trip trip) {
        return tripUseCase.getTotal(trip);
    }
}
