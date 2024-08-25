package controller;

import model.Expense;
import model.ExpenseCategory;
import service.ExpenseUseCase;

import java.util.List;

public class ExpenseController {
    private final ExpenseUseCase expenseUseCase;

    public ExpenseController(ExpenseUseCase expenseUseCase) {
        this.expenseUseCase = expenseUseCase;
    }

    public void addExpense(Expense expense) {
        expenseUseCase.addExpense(expense);
    }

    public List<Expense> getExpensesByCategory(ExpenseCategory category) {
        return expenseUseCase.getExpensesByCategory(category);
    }
}
