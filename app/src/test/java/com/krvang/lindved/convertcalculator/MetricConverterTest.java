package com.krvang.lindved.convertcalculator;

import com.krvang.lindved.convertcalculator.bll.IMetricConverter;
import com.krvang.lindved.convertcalculator.bll.MetricConverter;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by Lindved on 19-03-2018.
 */

public class MetricConverterTest {

    private IMetricConverter mMetricConverter;

    @Test
    public void kilometersToMiles_correct(){
        mMetricConverter = new MetricConverter();
        String expectedResult, result;

        expectedResult = "0,62";
        result = mMetricConverter.kilometersToMiles(1f);
        Assert.assertEquals(expectedResult, result);

        expectedResult = "3,11";
        result = mMetricConverter.kilometersToMiles(5f);
        Assert.assertEquals(expectedResult, result);

        expectedResult = "6,21";
        result = mMetricConverter.kilometersToMiles(10f);
        Assert.assertEquals(expectedResult, result);

        expectedResult = "18,64";
        result = mMetricConverter.kilometersToMiles(30f);
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void milesToKilometers_correct(){
        mMetricConverter = new MetricConverter();
        String expectedResult, result;

        expectedResult = "1,61";
        result = mMetricConverter.milesToKilometers(1f);
        Assert.assertEquals(expectedResult, result);

        expectedResult = "8,05";
        result = mMetricConverter.milesToKilometers(5f);
        Assert.assertEquals(expectedResult, result);

        expectedResult = "16,09";
        result = mMetricConverter.milesToKilometers(10f);
        Assert.assertEquals(expectedResult, result);

        expectedResult = "48,28";
        result = mMetricConverter.milesToKilometers(30f);
        Assert.assertEquals(expectedResult, result);
    }
}
