package com.androidmadeeasy.movielistarch.api;

import com.androidmadeeasy.movielistarch.model.MovieDbResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {

    @GET("movie/popular")
    Call<MovieDbResponse> getPopularMovies(@Query("api_key") String apiKey);
}
