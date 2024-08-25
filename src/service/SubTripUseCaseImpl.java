package service;

import model.Expense;
import model.SubTrip;

import java.util.List;

public class SubTripUseCaseImpl implements SubTripUseCase {
    @Override
    public SubTrip createSubTrip(String name, int numberOfPeople) {
        return new SubTrip(name, numberOfPeople);
    }

    @Override
    public void addExpenseToSubTrip(SubTrip subTrip, Expense expense) {
        subTrip.addExpense(expense);
    }

    @Override
    public double getSubTripTotalCost(SubTrip subTrip) {
        return subTrip.getTotalCost();
    }

    @Override
    public double calculateSubTripExpensePerPerson(SubTrip subTrip) {
        return subTrip.calculateExpensePerPerson();
    }

    @Override
    public String getSubTripName(SubTrip subTrip) {
        return subTrip.getName();
    }

    @Override
    public List<Expense> getSubTripExpenses(SubTrip subTrip) {
        return subTrip.getExpenses();
    }
}
