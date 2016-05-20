package com.udacity.learning.mysunshineapp.model;

import java.util.List;

/**
 * Created by Subbu on 5/20/16.
 */
public class WeatherData {

    private long dt;
    private String pressure;
    private String humidity;
    private String speed;
    private String clouds;
    private TemperatureData temp;
    private List<WeatherDesc> weather;

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

    public void setWeather(List<WeatherDesc> weather) {
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
}
