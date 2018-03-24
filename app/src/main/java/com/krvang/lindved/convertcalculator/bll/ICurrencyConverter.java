package com.krvang.lindved.convertcalculator.bll;

/**
 * Created by Lindved on 24-03-2018.
 */

public interface ICurrencyConverter {

    /**
     * Converts the parsed amount of Danish Crowns into American dollars.
     * @param crowns The amount of Danish Crowns.
     * @return The amount of Danish Crowns in American dollars.
     */
    float convertCrownsToDollars(float crowns);

    /**
     * Converts the parsed amount of American dollars int Danish Crowns.
     * @param dollars The amount of dollars.
     * @return The amount of dollars in Danish Crowns.
     */
    float convertDollarsToCrowns(float dollars);
}
