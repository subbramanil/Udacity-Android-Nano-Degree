package com.udacity.learning.mysunshineapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.learning.mysunshineapp.model.WeatherData;

/**
 * A placeholder fragment containing a simple view.
 */
public class WeatherDetailActivityFragment extends Fragment {

    private static final String TAG = WeatherDetailActivityFragment.class.getSimpleName();
    private WeatherData data;

    public WeatherDetailActivityFragment() {
    }

    public static WeatherDetailActivityFragment newInstance(WeatherData data) {
        WeatherDetailActivityFragment fragment = new WeatherDetailActivityFragment();
        Bundle args = new Bundle();
        args.putParcelable("weather_data", data);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get arguments and fetch the Weather data sent from activity
        Bundle args = getArguments();
        Log.d(TAG, "onCreate: args: " + getArguments());
        if (args != null) {
            data = args.getParcelable("weather_data");
        }
        Log.d(TAG, "onCreate: Weather Data: " + data);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_detail, container, false);

        TextView cloudDetailTextView = (TextView) view.findViewById(R.id.cloud_detail);
        cloudDetailTextView.setText(data.getWeather().get(0).getDescription());

        return view;
    }

}
