package com.krvang.lindved.convertcalculator.bll;

/**
 * Created by Lindved on 24-03-2018.
 */

public class NonAccurateCurrencyConverter implements ICurrencyConverter {

    private static float EXCHANGE_RATE = 6.03f;

    @Override
    public float convertCrownsToDollars(float crowns) {
        return crowns / EXCHANGE_RATE;
    }

    @Override
    public float convertDollarsToCrowns(float dollars) {
        return dollars * EXCHANGE_RATE;
    }
}
