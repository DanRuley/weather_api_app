package com.example.weatherapi_tutorial;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class WeatherReportView {

    private MainActivity ctx;
    Button btn_CityID, btn_getWeatherByID, btn_getWeatherByName;
    EditText nameInput, idInput;
    ListView lv_weatherReport;


    public WeatherReportView(MainActivity _ctx) {

        ctx = _ctx;

        initializeComponents();
    }

    private void initializeComponents() {

        btn_CityID = ctx.findViewById(R.id.btn_getCityID);
        btn_getWeatherByID = ctx.findViewById(R.id.btn_getWeatherByCityID);
        btn_getWeatherByName = ctx.findViewById(R.id.btn_getWeatherByCityName);

        nameInput = ctx.findViewById(R.id.nameInput);
        idInput = ctx.findViewById(R.id.idInput);
        lv_weatherReport = ctx.findViewById(R.id.lv_weatherReports);

        btn_CityID.setOnClickListener(v -> ctx.getDataService().getCityID(nameInput.getText().toString(), new WeatherDataService.VolleyResponseListener<String>() {
            @Override
            public void onResponse(String cityID) {
                Toast.makeText(ctx, "City ID = " + cityID, Toast.LENGTH_SHORT).show();
                idInput.setText(cityID);
            }

            @Override
            public void onError(String message) {
                Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show();
            }
        }));

        btn_getWeatherByID.setOnClickListener(v -> ctx.getDataService().getCityForecastByID(idInput.getText().toString(), new WeatherDataService.VolleyResponseListener<List<WeatherReportModel>>() {
            @Override
            public void onResponse(List<WeatherReportModel> reports) {
                populateReportView(reports);
            }

            @Override
            public void onError(String message) {
                Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show();
            }
        }));

        btn_getWeatherByName.setOnClickListener(v -> ctx.getDataService().getCityForecastByName(nameInput.getText().toString(), new WeatherDataService.VolleyResponseListener<List<WeatherReportModel>>() {
            @Override
            public void onResponse(List<WeatherReportModel> reports) {
                populateReportView(reports);
            }

            @Override
            public void onError(String message) {
                Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show();
            }
        }));
    }

    private void populateReportView(List<WeatherReportModel> reports) {
        ArrayAdapter adapter = new ArrayAdapter(ctx, android.R.layout.simple_list_item_1, reports);
        lv_weatherReport.setAdapter(adapter);
    }

}
