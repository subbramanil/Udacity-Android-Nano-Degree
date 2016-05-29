package com.udacity.learning.mysunshineapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Subbu on 5/20/16.
 */
public class WeatherDesc implements Parcelable {

    private String main;
    private String description;
    private String icon;


    protected WeatherDesc(Parcel in) {
        main = in.readString();
        description = in.readString();
        icon = in.readString();
    }

    public static final Creator<WeatherDesc> CREATOR = new Creator<WeatherDesc>() {
        @Override
        public WeatherDesc createFromParcel(Parcel in) {
            return new WeatherDesc(in);
        }

        @Override
        public WeatherDesc[] newArray(int size) {
            return new WeatherDesc[size];
        }
    };

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "WeatherDesc{" +
                "main='" + main + '\'' +
                ", description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.main);
        parcel.writeString(this.description);
        parcel.writeString(this.icon);
    }
}
