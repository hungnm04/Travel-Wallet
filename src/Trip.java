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
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budget = budget;
        this.expenses = new ArrayList<>();
        this.subTrips = new ArrayList<>();
    }

    public void addExpense(Expense expense) {
        this.expenses.add(expense);
    }

    public void addSubTrip(SubTrip subTrip) {
        this.subTrips.add(subTrip);
    }

    public List<SubTrip> getSubTrips() {
        return subTrips;
    }

    public String getName() {
        return name;
    }

    public LocalDate getStartDate() {
        return LocalDate.from(startDate);
    }

    public LocalDate getEndDate() {
        return LocalDate.from(endDate);
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public double getBudget() {
        return budget;
    }

    public double getTotal() {
        double total = 0;
        for(var i : expenses){
            total += i.amount();
        }
        for(SubTrip subTrip : subTrips){
            total += subTrip.getTotalCost();
        }
        return total;
    }
//    @Override
//    public String toString() {
//        StringBuilder tripSummary = new StringBuilder();
//        tripSummary.append("Trip Name: ").append(name).append("\n");
//        tripSummary.append("Start Date: ").append(startDate).append("\n");
//        tripSummary.append("End Date: ").append(endDate).append("\n");
//        tripSummary.append("Expenses:\n");
//
//        for (Expense expense : expenses) {
//            tripSummary.append(expense.toString()).append("\n");
//        }
//
//        return tripSummary.toString();
//    }
}
