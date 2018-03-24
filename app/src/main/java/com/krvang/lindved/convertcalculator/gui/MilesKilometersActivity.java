package com.krvang.lindved.convertcalculator.gui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.krvang.lindved.convertcalculator.R;
import com.krvang.lindved.convertcalculator.bll.IMetricConverter;
import com.krvang.lindved.convertcalculator.bll.MetricConverter;

public class MilesKilometersActivity extends AppCompatActivity {

    public static String TAG = "TEST";
    public static String KEY_CONVERTING_BOOLEAN = "com.krvang.lindved.convertBoolean";
    public static String KEY_VALUE = "com.krvang.lindved.value";

    private TextView mTitleText, mAmountText, mResultText, mPostfixTest;
    private EditText mValueText;

    private boolean mIsConvertingFromMilesToKilometers;

    private IMetricConverter mMetricConverter;

    public static Intent getIntent(Context packageContext){
        Intent intent = new Intent(packageContext, MilesKilometersActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);
        initializeTextViews();

        if(savedInstanceState != null){
            mIsConvertingFromMilesToKilometers = !savedInstanceState.getBoolean(KEY_CONVERTING_BOOLEAN);
            float value = savedInstanceState.getFloat(KEY_VALUE);
            if(value != 0f) {
                mValueText.setText(value + "");
            }
        }

        mMetricConverter = new MetricConverter();

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
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.switch_action_item, menu);
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean(KEY_CONVERTING_BOOLEAN, mIsConvertingFromMilesToKilometers);
        try{
            float value = Float.parseFloat(mValueText.getText().toString());
            outState.putFloat(KEY_VALUE, value);
        }catch (NumberFormatException nfe){
            Log.e(TAG, "onSaveInstanceState: Value is not a float - Can be ignored");
        }
    }

    /**
     * Changes if we are converting from Miles to Kilometers or vice versa.
     */
    private void switchConvertFrom(){
        if(mIsConvertingFromMilesToKilometers)
            setKilometersToMiles();
        else
            setMilesToKilometers();
    }

    /**
     * Sets what should be displayed if we are converting from Miles to Kilometers.
     */
    private void setMilesToKilometers(){
        mIsConvertingFromMilesToKilometers = true;

        mTitleText.setText(R.string.milesToKilometersTitle);
        try{
            float value = Float.parseFloat(mValueText.getText().toString());
            String messagedToBeDisplayed = value + " " + getString(R.string.amountMiles);
            mAmountText.setText(messagedToBeDisplayed);
            displayConvertedValue(value);
            mPostfixTest.setText(R.string.kilometers);
        }catch (NumberFormatException nfe){
            Log.e(TAG, "setMilesToKilometers: Value is not a float - Can be ignored");
            setNoValueEntered();
        }
    }

    /**
     * Sets what should be displayed if we are converting from Kilometers to Miles.
     */
    private void setKilometersToMiles(){
        mIsConvertingFromMilesToKilometers = false;

        mTitleText.setText(R.string.kilometersToMiles);
        try{
            float value = Float.parseFloat(mValueText.getText().toString());
            String messagedToBeDisplayed = value + " " + getString(R.string.amountKilometers);
            mAmountText.setText(messagedToBeDisplayed);
            displayConvertedValue(value);
            mPostfixTest.setText(R.string.miles);
        }catch (NumberFormatException nfe){
            Log.e(TAG, "setKilometersToMiles: Value is not a float - Can be ignored");
            setNoValueEntered();
        }
    }

    private void displayConvertedValue(float initValue){
        String result;
        if(mIsConvertingFromMilesToKilometers)
            result = mMetricConverter.milesToKilometers(initValue);
        else
            result = mMetricConverter.kilometersToMiles(initValue);
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
        if(mIsConvertingFromMilesToKilometers)
            setMilesToKilometers();
        else
            setKilometersToMiles();
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

    private void initializeTextViews(){
        mTitleText = findViewById(R.id.txtTitle);
        mAmountText = findViewById(R.id.txtAmount);
        mResultText = findViewById(R.id.txtResult);
        mPostfixTest = findViewById(R.id.txtPostfix);
        mValueText = findViewById(R.id.txtValue);
    }
}
