package service;

import java.util.Currency;

public class USDCurrency implements CurrencyConverter {
    private static final double USD_TO_VND = 25125;

    @Override
    public double toVND(double value) {
        return value * USD_TO_VND;
    }

    @Override
    public double fromVND(double value) {
        return value / USD_TO_VND;
    }
}

public class EURCurrency implements Currency {
    private static final double EUR_TO_VND = 27486;

    @Override
    public double toVND(double value) {
        return value * EUR_TO_VND;
    }

    @Override
    public double fromVND(double value) {
        return value / EUR_TO_VND;
    }
}

