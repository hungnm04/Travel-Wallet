package model;

public class Expense {
    private final double amount;
    private final ExpenseCategory category;
    private final String currency;
    private final String description;

    public Expense(double amount, ExpenseCategory category, String currency, String description) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }
        if (currency == null || currency.isEmpty()) {
            throw new IllegalArgumentException("Currency cannot be null or empty");
        }
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        this.amount = amount;
        this.category = category;
        this.currency = currency;
        this.description = description;
    }

    public double getAmount() { return amount; }
    public ExpenseCategory getCategory() { return category; }
    public String getCurrency() { return currency; }
    public String getDescription() { return description; }

    @Override
    public String toString() {
        return String.format("%.2f %s - %s (%s)", amount, currency, description, category);
    }
}
