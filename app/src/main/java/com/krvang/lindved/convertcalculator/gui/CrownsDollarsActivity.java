package com.krvang.lindved.convertcalculator.gui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

        mCurrencyConverter = new NonAccurateCurrencyConverter();

        initializeViews();

        switchConvertFrom();

        findViewById(R.id.btnCalculate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleCalculateButton();
            }
        });
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

    /**
     * Switches the convert form.
     */
    private void switchConvertFrom(){
        if(mIsConvertingFromCrownsToDollars)
            setDollarsToCrowns();
        else
            setCrownsToDollars();
    }

    /**
     * Updates all the TextViews to show conversion from Crowns to Dollars.
     */
    private void setCrownsToDollars(){
        mIsConvertingFromCrownsToDollars = true;

        mTitleText.setText(R.string.CrownToDollars);
        try{
            float value = Float.parseFloat(mValueText.getText().toString());
            String messagedToBeDisplayed = value + " " + getString(R.string.CrownsIs);
            mAmountText.setText(messagedToBeDisplayed);
            displayConvertedValue(value);
            mPostfixTest.setText(R.string.Dollars);
        }catch (NumberFormatException nfe){
            Log.e(TAG, "setCrownsToDollars: Value is not a float - Can be ignored");
            setNoValueEntered();
        }
    }

    /**
     * Updates all the TextViews to show conversion from Dollars to Crowns.
     */
    private void setDollarsToCrowns(){
        mIsConvertingFromCrownsToDollars = false;

        mTitleText.setText(R.string.DollarsToCrowns);
        try{
            float value = Float.parseFloat(mValueText.getText().toString());
            String messagedToBeDisplayed = value + " " + getString(R.string.DollarsIs);
            mAmountText.setText(messagedToBeDisplayed);
            displayConvertedValue(value);
            mPostfixTest.setText(R.string.Crowns);
        }catch (NumberFormatException nfe){
            Log.e(TAG, "setDollarsToCrowns: Value is not a float - Can be ignored");
            setNoValueEntered();
        }
    }

    /**
     * Gets the converted value from the CurrencyConverter and displays it.
     * @param initValue The value the needs to be converted.
     */
    private void displayConvertedValue(float initValue){
        String result;
        if(mIsConvertingFromCrownsToDollars)
            result = mCurrencyConverter.convertCrownsToDollars(initValue);
        else
            result = mCurrencyConverter.convertDollarsToCrowns(initValue);
        mResultText.setText(result);
    }

    /**
     * If no value is entered - displays a message to the user. Else - Update the display.
     */
    private void handleCalculateButton(){
        if(mValueText.getText().toString().equals("")){
            displayToast(getString(R.string.pressCalculate), false);
            return;
        }
        if(mIsConvertingFromCrownsToDollars)
            setCrownsToDollars();
        else
            setDollarsToCrowns();
    }

    /**
     * Displays toast to the user.
     * @param message - The message the toast should display.
     * @param longToast - True if it is a long Toast. False if it is a short Toast.
     */
    private void displayToast(String message, boolean longToast){
        int length = longToast ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT;
        Toast.makeText(this, message, length).show();
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
