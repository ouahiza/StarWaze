package com.example.starwaze;

import com.example.starwaze.modeles.Apod;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APODApi {

    @GET("apod?api_key=hqjA9ljxF8AVy6AVgPPbvSl9l1LPiwzUzOYASlpN")
    Call<Apod> getApod();
}
