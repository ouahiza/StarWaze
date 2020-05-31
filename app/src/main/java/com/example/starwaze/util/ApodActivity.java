package com.example.starwaze.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.starwaze.APODApi;
import com.example.starwaze.R;
import com.example.starwaze.modeles.Apod;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.r0adkll.slidr.Slidr;


public class ApodActivity extends AppCompatActivity {
    private TextView apodTitle;
    private TextView apodExplanation;
    private ImageView apodImage;
    private Context context;
    public static final String BASE_URL = "https://api.nasa.gov/planetary/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_apod);
        getSupportActionBar().hide();

        //Slide Feature
        Slidr.attach(this);

        context = this;

        apodTitle = findViewById(R.id.title_apod_activity);
        apodExplanation = findViewById((R.id.explanation_apod_activity));
        apodImage = findViewById(R.id.image_apod_activity);

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
                    apodTitle.setText(new StringBuilder().append("Code: ").append(response.code()).toString());
                    return;
                }

                Apod apod = response.body();

                String titleContent = apod.getApodTitle();
                String explanationContent = apod.getExplanation();
                String hdurl = apod.getHdurl();

                apodTitle.append(titleContent);
                apodExplanation.append(explanationContent);
                apodExplanation.setMovementMethod(new ScrollingMovementMethod());

                Glide.with(context).load(hdurl).into(apodImage);
            }

            @Override
            public void onFailure(Call<Apod> call, Throwable t) {
                apodTitle.setText((t.getMessage()));
            }
        });

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            showRotationToast();
        }
    }

    public void showRotationToast() {
        StyleableToast.makeText(context, "Rotate your phone to see full picture", R.style.rotationToast).show();
    }
}
