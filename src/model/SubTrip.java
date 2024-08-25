package model;

import java.util.ArrayList;
import java.util.List;

public class SubTrip {
    private final String name;
    private final int numberOfPeople;
    private final List<Expense> expenses;

    public SubTrip(String name, int numberOfPeople) {
        if (numberOfPeople <= 0) {
            throw new IllegalArgumentException("Number of people must be positive");
        }
        this.name = name;
        this.numberOfPeople = numberOfPeople;
        this.expenses = new ArrayList<>();
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void addExpense(Expense expense) {
        this.expenses.add(expense);
    }

    public double getTotalCost() {
        double totalCost = 0;
        for (Expense expense : expenses) {
            totalCost += expense.amount();
        }
        return totalCost;
    }

    public double calculateExpensePerPerson() {
        return getTotalCost() / numberOfPeople;
    }

    public String getName() {
        return name;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }
}
