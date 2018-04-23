
package com.example.rohan.androidweatherapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wind {

    @SerializedName("speed")
    @Expose
    private double speed;
    @SerializedName("deg")
    @Expose
    private long deg;
    @SerializedName("gust")
    @Expose
    private double gust;

    public double getSpeed() {
        return speed;
    }


    public long getDeg() {
        return deg;
    }


    public double getGust() {
        return gust;
    }


}
