package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Trip {
    private final String name;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final double budget;
    private final List<Expense> expenses;
    private final List<SubTrip> subTrips;

    public Trip(String name, LocalDate startDate, LocalDate endDate, double budget) {
        if (budget <= 0) {
            throw new IllegalArgumentException("Budget must be positive");
        }
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date cannot be before start date");
        }
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budget = budget;
        this.expenses = new ArrayList<>();
        this.subTrips = new ArrayList<>();
    }

    public String getName() { return name; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public double getBudget() { return budget; }
    public List<Expense> getExpenses() { return new ArrayList<>(expenses); }
    public List<SubTrip> getSubTrips() { return new ArrayList<>(subTrips); }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public void addSubTrip(SubTrip subTrip) {
        subTrips.add(subTrip);
    }

    public double getTotal() {
        double total = 0;
        for (Expense expense : expenses) {
            total += expense.getAmount();
        }
        for (SubTrip subTrip : subTrips) {
            total += subTrip.getTotalCost();
        }
        return total;
    }
}