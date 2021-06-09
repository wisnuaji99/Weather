package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weather.Model.ForecastDayModel;
import com.example.weather.Model.HourModel;
import com.example.weather.adapter.ForecastAdapter;
import com.example.weather.adapter.HourAdapter;

import java.util.ArrayList;

public class DetailForecast extends AppCompatActivity {
    TextView tv_date, tv_maxtemp, tv_avgtemp, tv_mintemp, tv_totalprecip;
    private RecyclerView recyclerView;
    private HourAdapter hourAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<HourModel>listhour=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_forecast);
        ForecastDayModel forecastDay = getIntent().getParcelableExtra("ForecastDay");
        recyclerView = findViewById(R.id.list_hour);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        tv_date = findViewById(R.id.tv_date);
        tv_maxtemp = findViewById(R.id.tv_maxtemp);
        tv_avgtemp = findViewById(R.id.tv_avgtemp);
        tv_mintemp = findViewById(R.id.tv_mintemp);
        tv_totalprecip = findViewById(R.id.tv_totalprecip);

        tv_date.setText("Date : "+forecastDay.getDate());
        tv_maxtemp.setText("Maximum Temperature : "+Double.toString(forecastDay.getDay().getMaxtemp_c())+" Celcius");
        tv_avgtemp.setText("Average Temperature : "+Double.toString(forecastDay.getDay().getAvgtemp_c())+" Celcius");
        tv_mintemp.setText("Minimum Temperature : "+Double.toString(forecastDay.getDay().getMintemp_c())+" Celcius");
        tv_totalprecip.setText("Total Precipitation : "+Double.toString(forecastDay.getDay().getTotalprecip_mm())+" Milimeter");

        listhour=forecastDay.getHour();
        hourAdapter = new HourAdapter(listhour);
        recyclerView.setAdapter(hourAdapter);

    }
}