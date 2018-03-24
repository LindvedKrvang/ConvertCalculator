package com.krvang.lindved.convertcalculator.gui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.krvang.lindved.convertcalculator.R;
import com.krvang.lindved.convertcalculator.bll.ICurrencyConverter;
import com.krvang.lindved.convertcalculator.bll.NonAccurateCurrencyConverter;

/**
 * Created by Lindved on 24-03-2018.
 */

public class CrownsDollarsActivity extends AppCompatActivity {

    public static String TAG = "TEST";

    private TextView mTitleText, mAmountText, mResultText, mPostfixTest;
    private EditText mValueText;

    private boolean mIsConvertingFromCrownsToDollars;

    private ICurrencyConverter mCurrencyConverter;

    public static Intent getIntent(Context packageContext){
        Intent intent = new Intent(packageContext, CrownsDollarsActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);

        initializeViews();

        mCurrencyConverter = new NonAccurateCurrencyConverter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.switch_action_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_switch:{
                switchConvertFrom();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void switchConvertFrom(){
        if(mIsConvertingFromCrownsToDollars)
            setDollarsToCrowns();
        else
            setCrownsToDollars();
    }

    private void setCrownsToDollars(){
        mIsConvertingFromCrownsToDollars = true;

        mTitleText.setText("Converting Crowns to Dollars");
        try{
            float value = Float.parseFloat(mValueText.getText().toString());
            String messagedToBeDisplayed = value + " " + "Crowns is:";
            mAmountText.setText(messagedToBeDisplayed);
            displayConvertedValue(value);
            mPostfixTest.setText("Dollars");
        }catch (NumberFormatException nfe){
            Log.e(TAG, "setCrownsToDollars: Value is not a float - Can be ignored");
            setNoValueEntered();
        }
    }

    private void setDollarsToCrowns(){
        mIsConvertingFromCrownsToDollars = false;

        mTitleText.setText("Converting Dollars to Crowns");
        try{
            float value = Float.parseFloat(mValueText.getText().toString());
            String messagedToBeDisplayed = value + " " + "Dollars is:";
            mAmountText.setText(messagedToBeDisplayed);
            displayConvertedValue(value);
            mPostfixTest.setText("Crowns");
        }catch (NumberFormatException nfe){
            Log.e(TAG, "setDollarsToCrowns: Value is not a float - Can be ignored");
            setNoValueEntered();
        }
    }

    private void displayConvertedValue(float initValue){
        String result;
        if(mIsConvertingFromCrownsToDollars)
            result = mCurrencyConverter.convertCrownsToDollars(initValue);
        else
            result = mCurrencyConverter.convertDollarsToCrowns(initValue);
        mResultText.setText(result);
    }

    /**
     * Sets what should be displayed if no value has been entered by the user.
     */
    private void setNoValueEntered(){
        mAmountText.setText(R.string.noValue);
        mResultText.setText("");
        mPostfixTest.setText(R.string.pressCalculate);
    }

    private void initializeViews(){
        mTitleText = findViewById(R.id.txtTitle);
        mAmountText = findViewById(R.id.txtAmount);
        mResultText = findViewById(R.id.txtResult);
        mPostfixTest = findViewById(R.id.txtPostfix);
        mValueText = findViewById(R.id.txtValue);
    }
}
