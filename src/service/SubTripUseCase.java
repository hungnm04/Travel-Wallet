package service;

import model.Expense;
import model.SubTrip;

import java.util.List;

public interface SubTripUseCase {
    SubTrip createSubTrip(String name, int numberOfPeople);
    void addExpenseToSubTrip(SubTrip subTrip, Expense expense);
    double getSubTripTotalCost(SubTrip subTrip);
    double calculateSubTripExpensePerPerson(SubTrip subTrip);
    String getSubTripName(SubTrip subTrip);
    List<Expense> getSubTripExpenses(SubTrip subTrip);
}
