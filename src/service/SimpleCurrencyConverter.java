package service;

public class SimpleCurrencyConverter implements CurrencyConverter {

    @Override
    public double convert(double value, String fromCur, String toCur) {
        double rateFrom = getRate(fromCur);
        double rateTo = getRate(toCur);
        return (value * rateFrom) / rateTo;
    }

    private double getRate(String currency) {
        return switch (currency) {
            case "USD" -> 25125;
            case "EUR" -> 27486;
            case "GBP" -> 31500;
            case "VND" -> 1;
            default -> throw new IllegalArgumentException("Unsupported currency: " + currency);
        };
    }
}
