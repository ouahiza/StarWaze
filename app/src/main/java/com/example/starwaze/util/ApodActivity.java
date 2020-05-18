package com.example.starwaze.util;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.starwaze.APODApi;
import com.example.starwaze.R;
import com.example.starwaze.modeles.Apod;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApodActivity extends AppCompatActivity {
    private TextView apodTitle;
    public static final String BASE_URL = "https://api.nasa.gov/planetary/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apod);

        apodTitle = findViewById(R.id.title_apod_activity);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APODApi apodApi = retrofit.create(APODApi.class);

        Call<Apod> call = apodApi.getApod();

        call.enqueue(new Callback<Apod>() {
            @Override
            public void onResponse(Call<Apod> call, Response<Apod> response) {
                if (!response.isSuccessful()) {
                    apodTitle.setText("Code: " + response.code());
                    return;
                }

                Apod apod = response.body();

                String content = apod.getApodTitle();

                apodTitle.append(content);
            }

            @Override
            public void onFailure(Call<Apod> call, Throwable t) {
                apodTitle.setText((t.getMessage()));
            }
        });
    }
}