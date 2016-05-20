package com.udacity.learning.mysunshineapp.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.udacity.learning.mysunshineapp.R;
import com.udacity.learning.mysunshineapp.model.WeatherData;
import com.udacity.learning.mysunshineapp.model.WeatherDesc;

import java.io.InputStream;
import java.net.URL;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Subbu on 5/20/16.
 */
public class ForecastDataAdapter extends RecyclerView.Adapter<ForecastDataAdapter.ForecastViewHolder> {

    private static final String TAG = ForecastDataAdapter.class.getSimpleName();
    private static final String ICON_BASE_URL = "http://openweathermap.org/img/w/";
    private static LruCache<String, Bitmap> mMemoryCache = null;
    private final Context context;
    private final LayoutInflater inflater;
    private final List<WeatherData> weatherData;
    private ForecastItemClickListener itemClickListener;

    //region Constructor
    public ForecastDataAdapter(Context con, ForecastItemClickListener listener, List<WeatherData> data) {
        this.context = con;
        this.itemClickListener = listener;
        this.weatherData = data;
        this.inflater = LayoutInflater.from(context);

        // LRU Cache initialization
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        // Use 1/8th of the available memory for this memory cache.
        final int cacheSize = maxMemory / 8;

        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // The cache size will be measured in kilobytes rather than
                // number of items.
                return bitmap.getByteCount() / 1024;
            }
        };
    }

    //endregion

    public static void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    public static Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }

    //region Lifecycle methods
    @Override
    public ForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_forecast, parent, false);
        return new ForecastViewHolder(view);
    }

    //endregion

    // Interfaces & Classes declaration

    @Override
    public void onBindViewHolder(ForecastViewHolder holder, int position) {
        holder.position = position;
        WeatherData data = weatherData.get(position);

        Date date = new Date(data.getDt() * 1000);
        SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd", Locale.US);
        format.setTimeZone(TimeZone.getTimeZone("US/Central"));

        holder.dateTextView.setText(format.format(date));
        holder.maxTempTextView.setText(MessageFormat.format("Max: {0}", data.getTemp().getMax()));
        holder.minTempTextView.setText(MessageFormat.format("Min: {0}", data.getTemp().getMin()));
        WeatherDesc weatherDesc = data.getWeather().get(0);
        holder.cloudTextView.setText(weatherDesc.getDescription());
        String iconName = weatherDesc.getIcon();
        Bitmap icon = getBitmapFromMemCache(weatherDesc.getMain());

        Uri uri = Uri.parse(ICON_BASE_URL).buildUpon()
                .appendPath(iconName + ".png").build();
        if (icon == null) {
            Log.d(TAG, "onBindViewHolder: Icon not available; Fetch icons");
            ThumbnailTask task = new ThumbnailTask(weatherDesc.getMain(), position, holder, uri.toString());
            task.execute();
        } else {
            Log.d(TAG, "onBindViewHolder: Icon is already cached; use it");
            holder.weatherIconView.setImageBitmap(icon);
        }
    }

    @Override
    public int getItemCount() {
        return weatherData.size();
    }

    public interface ForecastItemClickListener {
        void onForecastItemClick(View view, int position);
    }

    //endregion

    //Util methods

    private static class ThumbnailTask extends AsyncTask {
        private int mPosition;
        private ForecastViewHolder mHolder;
        private String iconURL;
        private String iconName;


        public ThumbnailTask(String name, int position, ForecastViewHolder holder, String url) {
            mPosition = position;
            mHolder = holder;
            this.iconURL = url;
            this.iconName = name;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            Bitmap bitmap = (Bitmap) o;
            if (mHolder.position == mPosition) {
                mHolder.weatherIconView.setImageBitmap(bitmap);
                addBitmapToMemoryCache(iconName, bitmap);
            }
        }

        @Override
        protected Object doInBackground(Object[] params) {
            try {
                Log.d(TAG, "loadImageFromURL: " + iconURL);
                Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(iconURL).getContent());
                return bitmap;
            } catch (Exception e) {
                Log.e(TAG, "loadImageFromURL: Error in updating icons", e.getCause());
                e.printStackTrace();
                return null;
            }
        }
    }

    public class ForecastViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView cv;
        TextView dateTextView;
        TextView cloudTextView;
        TextView maxTempTextView;
        TextView minTempTextView;
        ImageView weatherIconView;
        int position;

        public ForecastViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.list_item_card);
            dateTextView = (TextView) cv.findViewById(R.id.list_item_date_textview);
            maxTempTextView = (TextView) cv.findViewById(R.id.list_item_max_temp_textview);
            minTempTextView = (TextView) cv.findViewById(R.id.list_item_min_temp_textview);
            cloudTextView = (TextView) cv.findViewById(R.id.list_item_weather_desc_textview);
            weatherIconView = (ImageView) cv.findViewById(R.id.list_item_weather_imageview);
        }

        @Override
        public void onClick(View v) {
            if (itemClickListener != null) {
                itemClickListener.onForecastItemClick(v, getPosition());
            }
        }
    }

    //endregion

}
