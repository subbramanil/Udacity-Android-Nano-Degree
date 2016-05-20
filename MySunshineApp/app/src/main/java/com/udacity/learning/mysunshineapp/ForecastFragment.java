package com.udacity.learning.mysunshineapp;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.udacity.learning.mysunshineapp.adapters.ForecastDataAdapter;
import com.udacity.learning.mysunshineapp.model.ForecastData;
import com.udacity.learning.mysunshineapp.model.WeatherData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Subbu on 5/20/16.
 */
public class ForecastFragment extends Fragment implements ForecastDataAdapter.ForecastItemClickListener {

    private static final String TAG = ForecastFragment.class.getSimpleName();
    private static final String BASE_SERVER_URL = "http://api.openweathermap.org/data/2.5/forecast/daily";
    private ForecastData forecastData;
    private ForecastDataAdapter forecastAdapter;
    private List<WeatherData> weatherDataList;

    //region Constructor
    public ForecastFragment() {
    }

    //endregion


    //region Lifecycle methods
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Get view components
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        RecyclerView forecastListView = (RecyclerView) rootView.findViewById(R.id.recyclerview_forecast);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        forecastListView.setLayoutManager(layoutManager);

        weatherDataList = new ArrayList<>();
        forecastAdapter = new ForecastDataAdapter(getActivity(), ForecastFragment.this, weatherDataList);
        forecastListView.setAdapter(forecastAdapter);

        // Fetch data using Async tasks
        // TODO: 5/20/16 Get location from the user and pass it to the Async task
        FetchWeatherForecastTask fetchForecastTask = new FetchWeatherForecastTask("Dallas");
        fetchForecastTask.execute();

        return rootView;
    }
    //endregion

    //region Event listeners
    @Override
    public void onForecastItemClick(View view, int position) {
        Log.d(TAG, "onForecastItemClick: Selected: " + forecastData.getList().get(position));
        // TODO: 5/20/16 Show more detailed view
    }

    //endregion

    //region Async tasks

    public class FetchWeatherForecastTask extends AsyncTask {

        private String city;
        private Uri uri;

        public FetchWeatherForecastTask(String city) {
            this.city = city;
        }

        @Override
        protected void onPreExecute() {
            Log.d(TAG, "onPreExecute: Prepare the URL for City: " + city);
            super.onPreExecute();
            uri = Uri.parse(BASE_SERVER_URL).buildUpon()
                    .appendQueryParameter("q", city)
                    .appendQueryParameter("units", "metric")
                    .appendQueryParameter("cnt", "7")
                    .appendQueryParameter("appid", getResources().getString(R.string.API_KEY)).build();
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            weatherDataList.clear();
            weatherDataList.addAll(forecastData.getList());
            forecastAdapter.notifyDataSetChanged();
        }

        @Override
        protected void onCancelled(Object o) {
            super.onCancelled(o);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Object doInBackground(Object[] params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            try {
                // Construct the URL for the OpenWeatherMap query
                // Possible parameters are avaiable at OWM's forecast API page, at
                // http://openweathermap.org/API#forecast
                URL url = new URL(uri.toString());

                // Create the request to OpenWeatherMap, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                }
                Gson gson = new Gson();
                forecastData = gson.fromJson(buffer.toString(), ForecastData.class);
                Log.d(TAG, "doInBackground: data: " + forecastData);
            } catch (IOException e) {
                Log.e("PlaceholderFragment", "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in attemping
                // to parse it.
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("PlaceholderFragment", "Error closing stream", e);
                    }
                }
            }

            return null;
        }
    }

    //endregion

}
