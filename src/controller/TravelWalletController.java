package controller;

import java.time.LocalDate;
import model.Expense;
import model.SubTrip;
import model.Trip;
import service.TravelWalletUseCase;

public class TravelWalletController {
    private final TravelWalletUseCase travelWalletUseCase;

    public TravelWalletController(TravelWalletUseCase travelWalletUseCase) {
        this.travelWalletUseCase = travelWalletUseCase;
    }

    public Trip createTrip(String name, LocalDate startDate, LocalDate endDate, double budget) {
        return travelWalletUseCase.createTrip(name, startDate, endDate, budget);
    }

    public boolean addExpense(String tripName, Expense expense) {
        return travelWalletUseCase.addExpense(tripName, expense);
    }

    public boolean addSubTrip(String tripName, SubTrip subTrip) {
        return travelWalletUseCase.addSubTrip(tripName, subTrip);
    }

    public void printTripSummary(String tripName, String currency) {
        travelWalletUseCase.printTripSummary(tripName, currency);
    }
}