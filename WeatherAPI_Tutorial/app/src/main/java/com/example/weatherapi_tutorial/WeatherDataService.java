package com.example.weatherapi_tutorial;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WeatherDataService {

    public static final int NUM_FORECAST_DAYS = 6;
    public static final String QUERY_CITY_ID_URL = "https://www.metaweather.com/api/location/search/?query=";
    public static final String QUERY_WEATHER_ID_URL = "https://www.metaweather.com/api/location/";

    private Context ctx;

    public WeatherDataService(Context _ctx) {
        this.ctx = _ctx;
    }

    /**
     * The three methods making Json Requests operate asynchronously - therefore when we attach GUI elements to those methods we cannot just use a return value.
     * Instead, we create an anonymous class instance of this interface and implement the response/error callbacks for the async requests.
     */
    public interface VolleyResponseListener<T> {
        void onResponse(T message);

        void onError(String message);
    }

    void getCityID(String cityName, final VolleyResponseListener<String> listener) {
        String url = QUERY_CITY_ID_URL + cityName;

        //The jsonRequest provides a jsonArray for POST requests; obviously, for GET requests this is not relevant.
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, url, null, response -> {
            try {
                System.out.println(response.toString());
                JSONObject cityInfo = response.getJSONObject(0);
                String cityID = cityInfo.getString("woeid");
                listener.onResponse(cityID);
            } catch (JSONException e) {
                listener.onError(e.getMessage());
            }
        }, error -> {
            listener.onError(error.getMessage());
        });

        RequestSingleton.getInstance(ctx).addToRequestQueue(req);
    }

    public void getCityForecastByID(String cityID, final VolleyResponseListener<List<WeatherReportModel>> listener) {
        List<WeatherReportModel> report = new ArrayList<>();
        String url = QUERY_WEATHER_ID_URL + cityID;

        //get json object
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                System.out.println(response.toString());
                JSONArray weather_list = response.getJSONArray("consolidated_weather");
                Gson gson = new Gson();

                for (int i = 0; i < NUM_FORECAST_DAYS; i++) {
                    JSONObject weatherDay = (JSONObject) weather_list.get(i);
                    WeatherReportModel day = gson.fromJson(weatherDay.toString(), WeatherReportModel.class);
                    report.add(day);
                    System.out.println(day);
                }
                listener.onResponse(report);
            } catch (JSONException e) {
                listener.onError(e.getMessage());
            }
        }, error -> {
            listener.onError(error.getMessage());
        });

        RequestSingleton.getInstance(ctx).addToRequestQueue(req);
    }

    /**
     * Chain together the cityID and weatherReportByID requests to get weather reports by name.
     * @param cityName The name of the city
     * @param listener The callback
     */
    public void getCityForecastByName(String cityName, final VolleyResponseListener<List<WeatherReportModel>> listener) {
        getCityID(cityName, new VolleyResponseListener<String>() {

            @Override
            public void onResponse(String cityID) {

                getCityForecastByID(cityID, new VolleyResponseListener<List<WeatherReportModel>>() {
                    @Override
                    public void onResponse(List<WeatherReportModel> reports) {
                        listener.onResponse(reports);
                    }

                    @Override
                    public void onError(String message) {
                        listener.onError(message);
                    }
                });
            }

            @Override
            public void onError(String message) {
                listener.onError(message);
            }
        });
    }
}
