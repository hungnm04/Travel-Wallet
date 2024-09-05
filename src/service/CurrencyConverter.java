package service;

public interface CurrencyConverter {
    double convert(double value, String fromCurrency, String toCurrency);
}