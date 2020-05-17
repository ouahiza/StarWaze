package com.example.starwaze.util;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.starwaze.APODAPI;
import com.example.starwaze.R;
import com.example.starwaze.modeles.Apod;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApodActivity extends AppCompatActivity {
    private TextView titleApod;
    public static final String BASE_URL = "https://api.nasa.gov/planetary/";
    public static Retrofit retrofit = null;

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apod);

        titleApod = findViewById(R.id.title_apod_activity);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APODAPI apodApi = retrofit.create((APODAPI.class));

        Call<List<Apod>> call = apodApi.loadApods();

        call.enqueue(new Callback<List<Apod>>() {
            @Override
            public void onResponse(Call<List<Apod>> call, Response<List<Apod>> response) {

                if(!response.isSuccessful()){
                    titleApod.setText(("Code: " + response.code()));
                    return;
                }
                String content = "";
                List<Apod> apods = response.body();
                for (Apod apod : apods){
                    content += "Title: " + apod.getApodTitle();

                    titleApod.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Apod>> call, Throwable t) {
                titleApod.setText((t.getMessage()));
            }
        });

    }
}
