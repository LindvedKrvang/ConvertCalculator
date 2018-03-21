package com.krvang.lindved.convertcalculator.bll;

import java.text.DecimalFormat;
import java.util.Formatter;

/**
 * Created by Lindved on 19-03-2018.
 */

public class MetricConverter implements IMetricConverter {

    private static float CONVERT_VALUE = 0.62137f;


    private DecimalFormat mFormatter;

    public MetricConverter(){
        mFormatter = new DecimalFormat("#.##");
    }

    @Override
    public String milesToKilometers(float miles) {
        return mFormatter.format(miles / CONVERT_VALUE);
    }

    @Override
    public String kilometersToMiles(float km) {
        return mFormatter.format(km * CONVERT_VALUE);
    }
}
