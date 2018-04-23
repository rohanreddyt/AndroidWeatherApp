package com.example.rohan.androidweatherapp.request;

import com.example.rohan.androidweatherapp.model.WeatherUpdate;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface Service {
    @GET("weather")
    Call<WeatherUpdate> getWeatherUpdate(@Query("zip") String zipCode,
                                         @Query("appid") final String API_KEY);
    // @Query("country code") String countryCode,
}
