package controller;

import model.Expense;
import model.SubTrip;
import service.SubTripUseCase;

import java.util.List;

public class SubTripController {
    private final SubTripUseCase subTripUseCase;

    public SubTripController(SubTripUseCase subTripUseCase) {
        this.subTripUseCase = subTripUseCase;
    }

    public SubTrip createSubTrip(String name, int numberOfPeople) {
        return subTripUseCase.createSubTrip(name, numberOfPeople);
    }

    public void addExpenseToSubTrip(SubTrip subTrip, Expense expense) {
        subTripUseCase.addExpenseToSubTrip(subTrip, expense);
    }

    public double getSubTripTotalCost(SubTrip subTrip) {
        return subTripUseCase.getSubTripTotalCost(subTrip);
    }

    public double calculateSubTripExpensePerPerson(SubTrip subTrip) {
        return subTripUseCase.calculateSubTripExpensePerPerson(subTrip);
    }

    public String getSubTripName(SubTrip subTrip) {
        return subTripUseCase.getSubTripName(subTrip);
    }

    public List<Expense> getSubTripExpenses(SubTrip subTrip) {
        return subTripUseCase.getSubTripExpenses(subTrip);
    }
}
