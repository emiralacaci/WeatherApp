package com.example.weatherapp;

import com.google.gson.annotations.SerializedName;

public class Example {
    @SerializedName("main")
    Main main;
    @SerializedName("wind")
    Wind wind;
/*
    @SerializedName("weather")
    Weather weather;

    public Weather getWeatherr() {
        return weather;
    }

    public void setWeatherr(Weather weather) {
        this.weather = weather;
    }

 */

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
