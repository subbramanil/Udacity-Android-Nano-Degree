package com.udacity.learning.mysunshineapp.model;

/**
 * Created by Subbu on 5/20/16.
 */
public class TemperatureData {

    private String day;
    private String night;
    private String morn;
    private String eve;
    private String max;
    private String min;

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
}
