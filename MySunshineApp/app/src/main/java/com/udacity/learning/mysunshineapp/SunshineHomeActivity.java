package com.udacity.learning.mysunshineapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class SunshineHomeActivity extends AppCompatActivity {


    //region Lifecycle methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ForecastFragment())
                    .commit();
        }
    }

    //endregion
}
