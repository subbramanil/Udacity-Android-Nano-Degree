package com.udacity.learning.mysunshineapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.learning.mysunshineapp.model.WeatherData;

public class WeatherDetailActivity extends AppCompatActivity {

    private static final String HASH_TAG = "My Sunshine App";
    private static final String BASE_URL = "http://openweathermap.org/img/w/";
    private static final String TAG = WeatherDetailActivity.class.getSimpleName();
    private WeatherData mWeatherData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // get the received intent and obtain the weather data passed from the SunshineHomeActivity
        Intent recdIntent = getIntent();
        if (recdIntent != null) {
            mWeatherData = recdIntent.getParcelableExtra("selected_weather_data");
            Log.d(TAG, "onCreate: weatherData: " + mWeatherData);
            Toast.makeText(this, mWeatherData.toString(), Toast.LENGTH_SHORT).show();
        }

        TextView cloudDetailTextView = (TextView) findViewById(R.id.cloud_detail);
        ImageView weatherBGImageView = (ImageView) findViewById(R.id.weather_bground);

        Picasso.with(this).load(BASE_URL + mWeatherData.getWeather().get(0).getIcon() + ".png").into(weatherBGImageView);

        cloudDetailTextView.setText(mWeatherData.toString());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_weather_detail, menu);

        // Locate MenuItem with ShareActionProvider
        MenuItem item = menu.findItem(R.id.action_share);

        // Fetch and store ShareActionProvider
        ShareActionProvider mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        // like when the user selects a new piece of data they might like to share.
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(createShareForecastIntent());
        } else {
            Log.d(TAG, "Share Action Provider is null?");
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_show_map) {
            SharedPreferences sharedPreferences = PreferenceManager
                    .getDefaultSharedPreferences(this);
            String location = sharedPreferences.getString(getString(R.string.location_pref_key), "Dallas");
            Uri geoLocation = Uri.parse("geo:0,0").buildUpon()
                    .appendQueryParameter("q", location)
                    .build();
            showMap(geoLocation);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private Intent createShareForecastIntent() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, mWeatherData.getWeather().get(0) + HASH_TAG);
        return shareIntent;
    }

    public void showMap(Uri geoLocation) {
        Intent intent = new Intent(Intent.ACTION_VIEW, geoLocation);
        intent.setData(geoLocation);
        intent.setPackage("com.google.android.apps.maps");
        if (intent.resolveActivity(this.getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
