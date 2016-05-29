package com.udacity.learning.mysunshineapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Subbu on 5/20/16.
 */
public class WeatherData implements Parcelable {

    private long dt;
    private String pressure;
    private String humidity;
    private String speed;
    private String clouds;
    private TemperatureData temp;
    private ArrayList<WeatherDesc> weather;

    protected WeatherData(Parcel in) {
        dt = in.readLong();
        pressure = in.readString();
        humidity = in.readString();
        speed = in.readString();
        clouds = in.readString();
        temp = in.readParcelable(TemperatureData.class.getClassLoader());
        weather = in.createTypedArrayList(WeatherDesc.CREATOR);
    }

    public static final Creator<WeatherData> CREATOR = new Creator<WeatherData>() {
        @Override
        public WeatherData createFromParcel(Parcel in) {
            return new WeatherData(in);
        }

        @Override
        public WeatherData[] newArray(int size) {
            return new WeatherData[size];
        }
    };

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getClouds() {
        return clouds;
    }

    public void setClouds(String clouds) {
        this.clouds = clouds;
    }

    public List<WeatherDesc> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<WeatherDesc> weather) {
        this.weather = weather;
    }

    public TemperatureData getTemp() {
        return temp;
    }

    public void setTemp(TemperatureData temp) {
        this.temp = temp;
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "dt=" + dt +
                ", pressure='" + pressure + '\'' +
                ", humidity='" + humidity + '\'' +
                ", speed='" + speed + '\'' +
                ", clouds='" + clouds + '\'' +
                ", temp=" + temp +
                ", weather=" + weather +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeLong(this.dt);
        parcel.writeString(this.pressure);
        parcel.writeString(this.humidity);
        parcel.writeString(this.speed);
        parcel.writeString(this.clouds);
        parcel.writeParcelable(this.temp, flags);
        parcel.writeTypedList(this.weather);
    }
}
