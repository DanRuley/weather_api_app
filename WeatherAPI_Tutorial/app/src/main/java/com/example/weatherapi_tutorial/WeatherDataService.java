package com.example.weatherapi_tutorial;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WeatherDataService {

    public static final String QUERY_CITY_ID_URL = "https://www.metaweather.com/api/location/search/?query=";
    public static final String QUERY_WEATHER_ID_URL = "https://www.metaweather.com/api/location/";

    private Context ctx;

    public WeatherDataService(Context _ctx) {
        this.ctx = _ctx;
    }

    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(String cityID);
    }

    void getCityID(String cityName, final VolleyResponseListener listener) {
        String url = QUERY_CITY_ID_URL + cityName;

        //The jsonRequest provides a jsonArray for POST requests; obviously, for GET requests this is not relevant.
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, url, null, response -> {
            try {
                JSONObject cityInfo = response.getJSONObject(0);
                String cityID = cityInfo.getString("woeid");
                listener.onResponse(cityID);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            listener.onError(error.getMessage());
        });

        RequestSingleton.getInstance(ctx).addToRequestQueue(req);
    }

    public void getCityForecastByID(String cityID) {
        List<WeatherReportModel> report = new ArrayList<>();
        String url = QUERY_WEATHER_ID_URL + cityID;

        //get json object
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                JSONArray weather_list = response.getJSONArray("consolidated_weather_list");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {});

        RequestSingleton.getInstance(ctx).addToRequestQueue(req);

        //get "consolidated_weather" - JSON array of 6 JSON objs

    }

    public List<WeatherReportView> getCityForecastByName(String cityName) {
        return null;
    }


}
