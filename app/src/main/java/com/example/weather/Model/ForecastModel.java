package com.example.weather.Model;

import java.util.ArrayList;

public class ForecastModel {
    private ArrayList<ForecastDayModel> forecastday;

    public ArrayList<ForecastDayModel> getForecastday() {
        return forecastday;
    }

    public void setForecastday(ArrayList<ForecastDayModel> forecastday) {
        this.forecastday = forecastday;
    }
}
