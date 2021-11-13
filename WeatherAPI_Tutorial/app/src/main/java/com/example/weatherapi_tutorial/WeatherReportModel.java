package com.example.weatherapi_tutorial;

public class WeatherReportModel {

    private long id;
    private String weather_state_name;
    private String weather_state_abbr;
    private String created;
    private String applicable_date;
    private double min_temp;
    private double max_temp;
    private double the_temp;
    private double wind_speed;
    private double wind_direction;
    private int air_pressure;
    private int humidity;
    private double visibility;
    private int predictability;

    public WeatherReportModel(long id, String weather_state_name, String weather_state_abbr, String created, String applicable_date, double min_temp, double max_temp, double the_temp, double wind_speed, double wind_direction, int air_pressure, int humidity, double visibility, int predictability) {
        this.id = id;
        this.weather_state_name = weather_state_name;
        this.weather_state_abbr = weather_state_abbr;
        this.created = created;
        this.applicable_date = applicable_date;
        this.min_temp = min_temp;
        this.max_temp = max_temp;
        this.the_temp = the_temp;
        this.wind_speed = wind_speed;
        this.wind_direction = wind_direction;
        this.air_pressure = air_pressure;
        this.humidity = humidity;
        this.visibility = visibility;
        this.predictability = predictability;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWeather_state_name() {
        return weather_state_name;
    }

    public void setWeather_state_name(String weather_state_name) {
        this.weather_state_name = weather_state_name;
    }

    public String getWeather_state_abbr() {
        return weather_state_abbr;
    }

    public void setWeather_state_abbr(String weather_state_abbr) {
        this.weather_state_abbr = weather_state_abbr;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getApplicable_date() {
        return applicable_date;
    }

    public void setApplicable_date(String applicable_date) {
        this.applicable_date = applicable_date;
    }

    public double getMin_temp() {
        return min_temp;
    }

    public void setMin_temp(double min_temp) {
        this.min_temp = min_temp;
    }

    public double getMax_temp() {
        return max_temp;
    }

    public void setMax_temp(double max_temp) {
        this.max_temp = max_temp;
    }

    public double getThe_temp() {
        return the_temp;
    }

    public void setThe_temp(double the_temp) {
        this.the_temp = the_temp;
    }

    public double getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(double wind_speed) {
        this.wind_speed = wind_speed;
    }

    public double getWind_direction() {
        return wind_direction;
    }

    public void setWind_direction(double wind_direction) {
        this.wind_direction = wind_direction;
    }

    public int getAir_pressure() {
        return air_pressure;
    }

    public void setAir_pressure(int air_pressure) {
        this.air_pressure = air_pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getVisibility() {
        return visibility;
    }

    public void setVisibility(double visibility) {
        this.visibility = visibility;
    }

    public int getPredictability() {
        return predictability;
    }

    public void setPredictability(int predictability) {
        this.predictability = predictability;
    }

    @Override
    public String toString() {
        return "WeatherReportModel{" +
                "id=" + id +
                ", weather_state_name='" + weather_state_name + '\'' +
                ", weather_state_abbr='" + weather_state_abbr + '\'' +
                ", created='" + created + '\'' +
                ", applicable_date='" + applicable_date + '\'' +
                ", min_temp=" + min_temp +
                ", max_temp=" + max_temp +
                ", the_temp=" + the_temp +
                ", wind_speed=" + wind_speed +
                ", wind_direction=" + wind_direction +
                ", air_pressure=" + air_pressure +
                ", humidity=" + humidity +
                ", visibility=" + visibility +
                ", predictability=" + predictability +
                '}';
    }
}
