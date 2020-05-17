package com.example.starwaze;

import com.example.starwaze.modeles.Apod;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APODAPI {
    @GET("apod")
Call<List<Apod>> loadApods();
}
