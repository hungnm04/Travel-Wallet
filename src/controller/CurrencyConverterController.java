package controller;

import service.CurrencyConverter;

public class CurrencyConverterController {
    private final CurrencyConverter currencyConverter;

    public CurrencyConverterController(CurrencyConverter currencyConverter) {
        this.currencyConverter = currencyConverter;
    }

    public double convert(double value, String fromCurrency, String toCurrency) {
        return currencyConverter.convert(value, fromCurrency, toCurrency);
    }
}
