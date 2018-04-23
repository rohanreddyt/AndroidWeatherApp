package com.example.rohan.androidweatherapp.request;

import com.example.rohan.androidweatherapp.model.WeatherUpdate;

import retrofit2.Call;

public interface BaseRequest {

    Call<WeatherUpdate> getWeatherUpdate(String zipCode, String key);

}
