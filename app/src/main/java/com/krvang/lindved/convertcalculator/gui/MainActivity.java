package com.krvang.lindved.convertcalculator.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.krvang.lindved.convertcalculator.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnMilesKilometers).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMilesKilometers();
            }
        });
    }

    private void goToMilesKilometers(){
        Intent intent = MilesKilometersActivity.getIntent(this);
        startActivity(intent);
    }
}
