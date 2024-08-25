package service;

import model.Expense;
import model.ExpenseCategory;

import java.util.List;

public interface ExpenseUseCase {
    void addExpense(Expense expense);
    List<Expense> getExpensesByCategory(ExpenseCategory category);
}
