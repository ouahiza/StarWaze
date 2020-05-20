package com.example.starwaze.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
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



public class ApodActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private TextView apodTitle;
    private TextView apodExplanation;
    private TextView apodDate;
    private ImageView apodImage;
    private VideoView apodVideo;
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

        context = this;

        apodTitle = findViewById(R.id.title_apod_activity);
        apodExplanation = findViewById((R.id.explanation_apod_activity));
        apodDate = findViewById(R.id.date_apod_activity);
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
                    apodTitle.setText("Code: " + response.code());
                    return;
                }

                Apod apod = response.body();

                String titleContent = apod.getApodTitle();
                String explanationContent = apod.getExplanation();
                Date dateContent = apod.getDate();
                String url = apod.getUrl();

                apodTitle.append(titleContent);
                apodDate.append(dateContent.toString());
                apodExplanation.append(explanationContent);
                apodExplanation.setMovementMethod(new ScrollingMovementMethod());

                if (apod.getMedia_type().equals("image")) {

                    Glide.with(context).load(url).override(300, 200).into(apodImage);

                } else if (apod.getMedia_type().equals("video")) {

                    //apodVideo = (VideoView) findViewById(R.id.video_apod_activity);

//                    MediaController mediaController;
//                    Log.e("entered", "playvideo");
//                    Log.e("path is", "" + url);
//                    progressDialog = ProgressDialog.show(ApodActivity.this, "", "Buffering video...", true);
//                    progressDialog.setCancelable(true);

                   // PlayVideo(url);
                }
            }

            @Override
            public void onFailure(Call<Apod> call, Throwable t) {
                apodTitle.setText((t.getMessage()));
            }
        });
    }

    /*private void PlayVideo(String url) {
        try {
            getWindow().setFormat(PixelFormat.TRANSLUCENT);
            MediaController mediaController = new MediaController(ApodActivity.this);
            mediaController.setAnchorView(apodVideo);

            Uri videoPath = Uri.parse(url);
            apodVideo.setMediaController(mediaController);
            apodVideo.setVideoURI(videoPath);
            apodVideo.requestFocus();
            apodVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                public void onPrepared(MediaPlayer mp) {
                    progressDialog.dismiss();
                    apodVideo.start();
                }
            });

        } catch (Exception e) {
            progressDialog.dismiss();
            System.out.println("Video Play Error :" + e.toString());
            finish();
        }

    }*/
}
