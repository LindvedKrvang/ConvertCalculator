package com.krvang.lindved.convertcalculator.gui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    private boolean mIsCovertingFromMilesToKilometers;

    public static Intent getIntent(Context packageContext){
        Intent intent = new Intent(packageContext, MilesKilometersActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miles_kilometers);

        initializeTextViews();
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

                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initializeTextViews(){
        mTitleText = findViewById(R.id.txtTitle);
        mAmountText = findViewById(R.id.txtAmount);
        mResultText = findViewById(R.id.txtResult);
        mPostfixTest = findViewById(R.id.txtPostfix);
        mValueText = findViewById(R.id.txtValue);
    }
}
