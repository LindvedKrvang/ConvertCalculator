package com.krvang.lindved.convertcalculator.gui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.krvang.lindved.convertcalculator.R;

public class MilesKilometersActivity extends AppCompatActivity {

    public static String TAG = "TEST";

    private TextView mTitleText, mAmountText, mResultText, mPostfixTest;
    private EditText mValueText;

    private boolean mIsConvertingFromMilesToKilometers;

    public static Intent getIntent(Context packageContext){
        Intent intent = new Intent(packageContext, MilesKilometersActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miles_kilometers);

        initializeTextViews();
        setMilesToKilometers();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.miles_kilometers_action_item, menu);
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
     * Changes if we are converting from Miles to Kilometers or vice versa.
     */
    private void switchConvertFrom(){
        if(mIsConvertingFromMilesToKilometers)
            setKilometersToMiles();
        else
            setMilesToKilometers();
        mIsConvertingFromMilesToKilometers = !mIsConvertingFromMilesToKilometers;
    }

    /**
     * Sets what should be displayed if we are converting from Miles to Kilometers.
     */
    private void setMilesToKilometers(){
        mTitleText.setText(R.string.milesToKilometersTitle);
        try{
            float value = Float.parseFloat(mValueText.getText().toString());
            String messagedToBeDisplayed = value + " " + getString(R.string.amountMiles);
            mAmountText.setText(messagedToBeDisplayed);
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
        mTitleText.setText(R.string.kilometersToMiles);
        try{
            float value = Float.parseFloat(mValueText.getText().toString());
            String messagedToBeDisplayed = value + " " + getString(R.string.amountKilometers);
            mAmountText.setText(messagedToBeDisplayed);
            mPostfixTest.setText(R.string.miles);
        }catch (NumberFormatException nfe){
            Log.e(TAG, "setKilometersToMiles: Value is not a float - Can be ignored");
            setNoValueEntered();
        }
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
