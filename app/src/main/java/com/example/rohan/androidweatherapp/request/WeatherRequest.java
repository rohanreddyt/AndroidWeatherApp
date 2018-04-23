package com.example.rohan.androidweatherapp.request;


import com.example.rohan.androidweatherapp.model.WeatherUpdate;
import retrofit2.Call;

public class WeatherRequest implements BaseRequest {

    WeatherAPI weatherAPI;

    public WeatherRequest(WeatherAPI weatherAPI) {
        this.weatherAPI = weatherAPI;
    }

    @Override
    public Call<WeatherUpdate> getWeatherUpdate(String zipCode, String key) {
       Call<WeatherUpdate> response = weatherAPI.getWeatherUpdate(zipCode,key);
       return response;
    }

}
