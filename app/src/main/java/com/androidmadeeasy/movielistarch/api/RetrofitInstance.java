package com.androidmadeeasy.movielistarch.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit mInstance = null;
    private static String BASE_URL = "https://api.themoviedb.org/3/";

    public static MovieApi getApi() {
        if (mInstance == null) {
            mInstance = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mInstance.create(MovieApi.class);
    }
}
