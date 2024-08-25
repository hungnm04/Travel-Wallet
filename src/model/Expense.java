package model;

public record Expense(double amount, ExpenseCategory category, String currency, String description) {
    public Expense {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }
    }


    @Override
    public String toString() {
        return String.format("%.2f %s - %s (%s)", amount, currency, description, category);
    }
}
