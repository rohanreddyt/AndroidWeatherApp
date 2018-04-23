package com.example.rohan.androidweatherapp.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;

import com.example.rohan.androidweatherapp.R;
import com.example.rohan.androidweatherapp.databinding.ActivityMainBinding;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.rohan.androidweatherapp.viemodels.WeatherDataViewModel;
import com.example.rohan.androidweatherapp.model.Main;
import com.example.rohan.androidweatherapp.model.Sys;
import com.example.rohan.androidweatherapp.model.Weather;
import com.example.rohan.androidweatherapp.model.WeatherUpdate;
import com.example.rohan.androidweatherapp.model.Wind;
import com.example.rohan.androidweatherapp.request.Client;
import com.example.rohan.androidweatherapp.request.Service;
import com.example.rohan.androidweatherapp.request.WeatherRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private WeatherUpdate weatherData;
    private static final String API_KEY = "7f052b5cfd378436b7a99c84582cbada";

    WeatherDataViewModel weatherDataViewModel = null;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        final String message = intent.getStringExtra("zipcode");


        new Thread(new Runnable() {
            @Override
            public void run() {
                getData(message,"us", API_KEY);
            }
        }).start();


    }

    //making the request
    private void getData(String zipCode, String countryCode, final String API_KEY){
        try {
            WeatherRequest request = new WeatherRequest(new Client().createService(Service.class));
            zipCode = zipCode + "," + countryCode;
            Call<WeatherUpdate> data = request.getWeatherUpdate(zipCode, API_KEY);
            data.enqueue(new Callback<WeatherUpdate>() {
                @Override
                public void onResponse(Call<WeatherUpdate> call, Response<WeatherUpdate> response) {
                    updateData(response.body());
                    Log.e("Rohan", "Success" + response.body());
                }

                @Override
                public void onFailure(Call<WeatherUpdate> call, Throwable t) {
                    Log.e("Rohan", "Failure");
                }
            });
        }
        catch(Exception e){
            Log.e("Rohan","Exception:"+e.getMessage());

        }
    }

    private void updateData(WeatherUpdate weatherUpdate){
        this.weatherData = weatherUpdate;
        Main mainData=null;
        Wind wind = null;
        Sys sysData = null;
        Weather weather = null;
        boolean showErrorMessage = false;
        String city ="",country="",description="",cloudness="";
        long humid=0,pressu = 0;
        double speed = 0;
        int temp = 0,max_temp = 0;
        if(weatherData!=null) {
           city = weatherData.getName();
            if(weatherData.getMain()!=null) {
                mainData = weatherData.getMain();
            }
           if(weatherData.getWind()!=null){
               wind=weatherData.getWind();
           }
           if(weatherData.getSys()!=null){
              sysData = weatherData.getSys();
           }
           if(weatherData.getWeather()!=null){
                weather = weatherData.getWeather().get(0);
           }
        }
        else{
            showErrorMessage = true;
        }
        if(mainData!=null) {
            humid = mainData.getHumidity();
            pressu = mainData.getPressure();
            temp = (int)(mainData.getFahrenheit(mainData.getTemp()));
            max_temp = (int)(mainData.getFahrenheit(mainData.getTempMax()));
        }
        if(wind!=null){
            speed = wind.getSpeed();
            long deg = wind.getDeg();
            double gust = wind.getGust();
        }
        if(sysData !=null){
            country = sysData.getCountry();
            long sunrise = sysData.getSunrise();
            long sunset = sysData.getSunset();
        }
        if(weather!=null){
           description = weather.getDescription();
           cloudness = weather.getMain();
        }
        weatherDataViewModel = new WeatherDataViewModel(this.getApplicationContext(), WordUtils.capitalizeFully(description),temp,max_temp,pressu,humid,
                speed,cloudness,city,country, showErrorMessage);
        showErrorMessage(showErrorMessage);

        binding.setWeatherDataViewModel(weatherDataViewModel);

    }
    public void showErrorMessage(boolean isError){
        if(isError){
            binding.errorMessage.setVisibility(View.VISIBLE);
            binding.mainLayout.setVisibility(View.GONE);
        }
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
