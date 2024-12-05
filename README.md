# **TravelWallet Documentation**

## **Overview**

TravelWallet is a Java application designed to help users manage and track their travel expenses. It allows users to create trips, add expenses, and manage subtrips, including calculating and summarizing expenses per person. The app supports multiple currencies and provides detailed reports on spending.

## **Features**

- **Add Trips**: Create trips with a name, start and end dates, and a budget.
- **Add Expenses**: Record expenses for trips and subtrips with categories and descriptions.
- **Manage Subtrips**: Add subtrips to a main trip and track expenses separately for each subtrip.
- **Expense Calculation**: Calculate the total cost and cost per person for each subtrip.
- **Currency Conversion**: Convert expenses and budgets between different currencies.
- **Summary Reports**: Generate summaries of total spending, remaining budgets, and detailed expense reports.

## **Installation**

1. Clone the Repository:
    
    git clone <repository-url>
    
2. Open the project in your preferred IDE (e.g., IntelliJ IDEA).
3. Run the `application.TravelWalletApp` class.

## **Code Structure**

- **TravelWalletApp**: Main application class for managing trips, expenses, and currency conversion.
- **Trip**: Class representing a trip with methods for adding expenses and subtrips.
- **SubTrip**: Class for managing expenses and calculating per-person costs within a subtrip.
- **Expense**: Class representing an expense with amount, category, currency, and description.
- **ExpenseCategory**: Enum defining various expense categories.
