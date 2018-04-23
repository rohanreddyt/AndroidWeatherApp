package com.example.rohan.androidweatherapp.request;


import com.example.rohan.androidweatherapp.model.WeatherUpdate;
import retrofit2.Call;

public class WeatherRequest implements BaseRequest {

    Service service;

    public WeatherRequest(Service service) {
        this.service = service;
    }

    @Override
    public Call<WeatherUpdate> getWeatherUpdate(String zipCode, String key) {
       Call<WeatherUpdate> response = service.getWeatherUpdate(zipCode,key);
       return response;
    }

}
