package com.udacity.learning.mysunshineapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Subbu on 5/20/16.
 */
public class TemperatureData implements Parcelable {

    private String day;
    private String night;
    private String morn;
    private String eve;
    private String max;
    private String min;

    protected TemperatureData(Parcel in) {
        day = in.readString();
        night = in.readString();
        morn = in.readString();
        eve = in.readString();
        max = in.readString();
        min = in.readString();
    }

    public static final Creator<TemperatureData> CREATOR = new Creator<TemperatureData>() {
        @Override
        public TemperatureData createFromParcel(Parcel in) {
            return new TemperatureData(in);
        }

        @Override
        public TemperatureData[] newArray(int size) {
            return new TemperatureData[size];
        }
    };

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getNight() {
        return night;
    }

    public void setNight(String night) {
        this.night = night;
    }

    public String getMorn() {
        return morn;
    }

    public void setMorn(String morn) {
        this.morn = morn;
    }

    public String getEve() {
        return eve;
    }

    public void setEve(String eve) {
        this.eve = eve;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    @Override
    public String toString() {
        return "TemperatureData{" +
                "day='" + day + '\'' +
                ", night='" + night + '\'' +
                ", morn='" + morn + '\'' +
                ", eve='" + eve + '\'' +
                ", max='" + max + '\'' +
                ", min='" + min + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.day);
        parcel.writeString(this.night);
        parcel.writeString(this.morn);
        parcel.writeString(this.eve);
        parcel.writeString(this.max);
        parcel.writeString(this.min);
    }
}
