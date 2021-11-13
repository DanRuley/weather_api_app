package com.example.weatherapi_tutorial;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONObject;

public class WeatherReportView {

    private MainActivity ctx;
    Button btn_CityID, btn_getWeatherByID, btn_getWeatherByName;
    EditText et_dataInput;
    ListView lv_weatherReport;


    public WeatherReportView(MainActivity _ctx) {

        ctx = _ctx;

        initializeComponents();
    }

    private void initializeComponents() {

        btn_CityID = ctx.findViewById(R.id.btn_getCityID);
        btn_getWeatherByID = ctx.findViewById(R.id.btn_getWeatherByCityID);
        btn_getWeatherByName = ctx.findViewById(R.id.btn_getWeatherByCityName);

        et_dataInput = ctx.findViewById(R.id.et_dataInput);
        lv_weatherReport = ctx.findViewById(R.id.lv_weatherReports);

        btn_CityID.setOnClickListener(v -> ctx.getDataService().getCityID(et_dataInput.getText().toString(), new WeatherDataService.VolleyResponseListener() {
            @Override
            public void onResponse(String cityID) {
                List<JSONObject> weather_list =

                Toast.makeText(ctx, "City ID = " + cityID, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String message) {
                Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show();
            }
        }));

        Toast.makeText(ctx, "You clicked get weather by id.", Toast.LENGTH_SHORT).show()
        btn_getWeatherByID.setOnClickListener(v -> ctx.getDataService().getCityForecastByID("44418"));

        btn_getWeatherByName.setOnClickListener(v -> Toast.makeText(ctx, "You typed " + et_dataInput.getText().toString(), Toast.LENGTH_SHORT).show());
    }
}
