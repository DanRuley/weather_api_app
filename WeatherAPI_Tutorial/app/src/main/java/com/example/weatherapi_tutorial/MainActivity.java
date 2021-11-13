package com.example.weatherapi_tutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    WeatherDataService service;
    WeatherReportView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        service = new WeatherDataService(MainActivity.this);
        view = new WeatherReportView(MainActivity.this);
    }

    public WeatherDataService getDataService() {
        return service;
    }

}