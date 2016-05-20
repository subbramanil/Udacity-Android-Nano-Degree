package com.udacity.learning.mysunshineapp.model;

import java.util.List;

/**
 * Created by Subbu on 5/20/16.
 */
public class ForecastData {

    private City city;
    private List<WeatherData> list;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<WeatherData> getList() {
        return list;
    }

    public void setList(List<WeatherData> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "ForecastData{" +
                "city=" + city +
                ", list=" + list +
                '}';
    }
}
