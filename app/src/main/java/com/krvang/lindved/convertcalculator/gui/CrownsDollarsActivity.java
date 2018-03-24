package com.krvang.lindved.convertcalculator.gui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.krvang.lindved.convertcalculator.R;

/**
 * Created by Lindved on 24-03-2018.
 */

public class CrownsDollarsActivity extends AppCompatActivity {

    public static Intent getIntent(Context packageContext){
        Intent intent = new Intent(packageContext, CrownsDollarsActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);
    }
}
