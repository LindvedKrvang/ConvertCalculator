package com.krvang.lindved.convertcalculator.bll;

import java.text.DecimalFormat;

/**
 * Created by Lindved on 24-03-2018.
 */

public class NonAccurateCurrencyConverter implements ICurrencyConverter {

    private static float EXCHANGE_RATE = 6.03f;
    private DecimalFormat mFormatter;

    public NonAccurateCurrencyConverter(){
        mFormatter = new DecimalFormat("#.##");
    }

    @Override
    public String convertCrownsToDollars(float crowns) {
        return mFormatter.format(crowns / EXCHANGE_RATE);
    }

    @Override
    public String convertDollarsToCrowns(float dollars) {
        return (dollars * EXCHANGE_RATE) + "";
    }
}
