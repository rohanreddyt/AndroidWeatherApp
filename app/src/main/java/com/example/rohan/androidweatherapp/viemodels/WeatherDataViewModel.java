package com.example.rohan.androidweatherapp.viemodels;


import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.example.rohan.androidweatherapp.R;

public class WeatherDataViewModel {
    ObservableField<String> description;
    ObservableField<String> temperature;
    ObservableField<String> pressure;
    ObservableField<String> windSpeed;
    ObservableField<String> cloudiness;
    ObservableField<String> location;
    ObservableField<String> humidity;
    ObservableField<String> maxTemp;
    ObservableBoolean showErrorMessage;
    ObservableBoolean showLoading = new ObservableBoolean(true);

    public WeatherDataViewModel(Context context, String description, int temperature, int max_temp, long pressure, long humidity, double windSpeed, String cloudiness, String city, String country, boolean showErrorMessage) {
        init();
        this.description.set(description);
        this.temperature.set(context.getString(R.string.temperature, temperature, "F"));
        this.pressure.set(context.getString(R.string.pressure, pressure, "hpa"));
        this.windSpeed.set(context.getString(R.string.wind_speed, String.format("%.1f", windSpeed), "m/s"));
        this.cloudiness.set(cloudiness);
        this.location.set(context.getString(R.string.set_location, "Measured", city, country));
        this.humidity.set(context.getString(R.string.humidity, humidity, "%"));
        this.maxTemp.set(context.getString(R.string.main_description,max_temp,"F"));
        this.showErrorMessage.set(showErrorMessage);

    }

    private void init() {
        description = new ObservableField<String>("");
        temperature = new ObservableField<>("");
        pressure = new ObservableField<>("");
        windSpeed = new ObservableField<>("");
        cloudiness = new ObservableField<>("");
        location = new ObservableField<>("");
        humidity = new ObservableField<>("");
        maxTemp = new ObservableField<>("");
        showErrorMessage = new ObservableBoolean(false);
    }

    public ObservableField<String> getDescription() {
        return description;
    }

    public ObservableField<String> getTemperature() {
        return temperature;
    }

    public ObservableField<String> getPressure() {
        return pressure;
    }

    public ObservableField<String> getWindSpeed() {
        return windSpeed;
    }

    public ObservableField<String> getCloudiness() {
        return cloudiness;
    }

    public ObservableField<String> getLocation() {
        return location;
    }

    public ObservableField<String> getHumidity() {
        return humidity;
    }

    public ObservableField<String> getMaxTemp() {
        return maxTemp;
    }

    public ObservableBoolean showError(){
        return showErrorMessage;
    }

    public ObservableBoolean getShowLoading(){
        return showLoading;
    }
}
