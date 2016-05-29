package com.udacity.learning.mysunshineapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.udacity.learning.mysunshineapp.model.WeatherData;

public class WeatherDetailActivity extends AppCompatActivity {

    private static final String TAG = WeatherDetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // get the received intent and obtain the weather data passed from the SunshineHomeActivity
        Intent recdIntent = getIntent();
        WeatherData weatherData = null;
        if (recdIntent != null) {
            weatherData = recdIntent.getParcelableExtra("selected_weather_data");
            Log.d(TAG, "onCreate: weatherData: " + weatherData);
            Toast.makeText(getApplicationContext(), weatherData.toString(), Toast.LENGTH_SHORT).show();
        }

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.detail_container, WeatherDetailActivityFragment.newInstance(weatherData))
                    .commit();
        }
    }

}
