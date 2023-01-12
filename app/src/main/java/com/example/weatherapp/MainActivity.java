package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    NumberFormat format;
    String url="https://api.openweathermap.org/data/2.5/weather?q={city}&appid={key}";
    String apikey="370e8b8a0c4172a520c98443b4622429";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        format=new DecimalFormat("#0.00");

    }

    public void deneme(View view){

        MediaPlayer mediaPlayer=MediaPlayer.create(MainActivity.this,R.raw.menu_sound);
        mediaPlayer.start();


        Gson gson=new GsonBuilder().setLenient().create();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherApi weatherApi=retrofit.create(WeatherApi.class);

        Call<Example> example=weatherApi.getWeather("İstanbul",apikey);

        example.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if(response.isSuccessful()){
                    Example mydata=response.body();
                    Main main=mydata.getMain();
                    //Weather weather=mydata.getWeatherr();
                    Wind wind = mydata.getWind();
                    Double temp=main.getTemp();
                    Integer temprature=(int)(temp-273.15);
                    System.out.println("DERECEMIZ: "+format.format(main.getTemp()-273.15)+
                            " en az :"+format.format(main.getTempMin()-273.15)+
                            " en fazla: "+format.format(main.getTempMax()-273.15)+
                            " pressure: "+main.getPressure()+
                            " nem: "+main.getHumidity());

                    //System.out.println("hava durumu: "+weather.getDescription());
                    System.out.println("ruzgar hizi: "+wind.getSpeed());
                }

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                System.out.println("onfailure");
            }
        });


    }


}