package com.example.rohan.androidweatherapp.activities;


import android.content.Intent;
import android.databinding.DataBindingUtil;

import com.example.rohan.androidweatherapp.R;
import com.example.rohan.androidweatherapp.databinding.ActivityStartBinding;
import com.example.rohan.androidweatherapp.request.Client;
import com.example.rohan.androidweatherapp.request.Service;
import com.example.rohan.androidweatherapp.request.WeatherRequest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends AppCompatActivity {

    ActivityStartBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       mBinding = DataBindingUtil.setContentView(this, R.layout.activity_start);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBinding.getInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value= mBinding.zipCode.getText().toString();
                Intent i = new Intent(StartActivity.this,MainActivity.class);
                i.putExtra("zipcode",value);
                startActivity(i);
            }
        });
    }
}
