public class SimpleCurrencyConverter implements CurrencyConverter {

    @Override
    public double convert(double value, String fromCur, String toCur) {
        if (fromCur.equals(toCur)) {
            return value;
        }

        // Convert from the source to VND
        double inVND = switch (fromCur) {
            case "USD" -> value * 25125;  // 1 USD = 25,125 VND
            case "EUR" -> value * 27486;  // 1 EUR = 27,486 VND
            case "GBP" -> value * 31500;  // 1 GBP = 31,500 VND
            case "VND" -> value;
            default -> throw new IllegalArgumentException("Unsupported currency: " + fromCur);
        };

        // Convert from VND to the target
        return switch (toCur) {
            case "USD" -> inVND / 25125;
            case "EUR" -> inVND / 27486;
            case "GBP" -> inVND / 31500;
            case "VND" -> inVND;
            default -> throw new IllegalArgumentException("Unsupported currency: " + toCur);
        };
    }
}
