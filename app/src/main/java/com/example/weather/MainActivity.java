package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weather.Model.ForecastDayModel;
import com.example.weather.Model.ForecastModel;
import com.example.weather.Model.WeatherModel;
import com.example.weather.Retrofit.ApiClient;
import com.example.weather.adapter.ForecastAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ForecastAdapter.OnForecastListener {
    private static final String TAG = "MyActivity";
    TextView tv_location, tv_wind, tv_pressure, tv_precip, tv_humidity, tv_cloud, tv_gust, tv_temp, tv_update, tv_condition;
    ArrayList<ForecastDayModel> forecastday=new ArrayList<>();
    ImageView curentimage;
    private RecyclerView recyclerView;
    private ForecastAdapter forecastAdapter;
    private RecyclerView.LayoutManager layoutManager;
    public static MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_location = findViewById(R.id.tv_location);
        tv_wind = findViewById(R.id.tv_wind);
        tv_pressure = findViewById(R.id.tv_pressure);
        tv_precip= findViewById(R.id.tv_precip);
        tv_humidity = findViewById(R.id.tv_humidity);
        tv_cloud = findViewById(R.id.tv_cloud);
        tv_gust = findViewById(R.id.tv_gust);
        tv_temp = findViewById(R.id.tv_temp);
        tv_update = findViewById(R.id.tv_update);
        tv_condition = findViewById(R.id.tv_condition);

        curentimage = findViewById(R.id.iv_current);

        recyclerView = findViewById(R.id.forecast_list);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mainActivity = this;
        getDataFromApi();
        getForecastFromApi();

    }

    private void getDataFromApi(){
        ApiClient.endpoint().getData()
                .enqueue(new Callback<WeatherModel>() {
                    @Override
                    public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                        tv_location.setText(response.body().getLocation().getName());
                        tv_wind.setText(Double.toString(response.body().getCurrent().getWind_kph())+" KM/H");
                        tv_pressure.setText(Double.toString(response.body().getCurrent().getPressure_mb())+" Milibar");
                        tv_precip.setText(Double.toString(response.body().getCurrent().getPrecip_mm())+" Milimeter");
                        tv_humidity.setText(Double.toString(response.body().getCurrent().getHumidity()));
                        tv_cloud.setText(Double.toString(response.body().getCurrent().getCloud()));
                        tv_gust.setText(Double.toString(response.body().getCurrent().getGust_kph())+" KPH");
                        tv_temp.setText(Double.toString((response.body().getCurrent().getTemp_c()))+" Celcius");
                        tv_update.setText(response.body().getCurrent().getLast_updated());
                        tv_condition.setText(response.body().getCurrent().getCondition().getText());
                        Picasso.get().load("https://"+ response.body().getCurrent().getCondition().getIcon()).into(curentimage);



                    }

                    @Override
                    public void onFailure(Call<WeatherModel> call, Throwable t) {
                        Log.d(TAG, "onFailure: Data Gagal Diambil");

                    }
                });

    }
    private void getForecastFromApi(){
        ApiClient.endpoint().getForecast()
                .enqueue(new Callback<WeatherModel>() {
                    @Override
                    public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                        forecastday=response.body().getForecast().getForecastday();
                        forecastAdapter = new ForecastAdapter(forecastday, mainActivity);
                        recyclerView.setAdapter(forecastAdapter);
                    }

                    @Override
                    public void onFailure(Call<WeatherModel> call, Throwable t) {

                    }
                });
    }

    @Override
    public void onForecastClick(int position) {
        Intent intent = new Intent(this,DetailForecast.class);
        intent.putExtra("ForecastDay", forecastday.get(position));
        startActivity(intent);
        Log.d(TAG, "onForecastClick: clicked");
    }
}