package service;

public interface CurrencyConverter {
    double convert(double value, String fromCur, String toCur);
}
