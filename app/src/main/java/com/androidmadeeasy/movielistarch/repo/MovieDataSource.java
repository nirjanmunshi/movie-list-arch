package com.androidmadeeasy.movielistarch.repo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.androidmadeeasy.movielistarch.R;
import com.androidmadeeasy.movielistarch.api.MovieApi;
import com.androidmadeeasy.movielistarch.api.RetrofitInstance;
import com.androidmadeeasy.movielistarch.model.Movie;
import com.androidmadeeasy.movielistarch.model.MovieDbResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDataSource extends PageKeyedDataSource<Long, Movie> {
    private MovieApi api;
    private Application application;

    public MovieDataSource(MovieApi api, Application application) {
        this.api = api;
        this.application = application;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull LoadInitialCallback<Long, Movie> callback) {
        api = RetrofitInstance.getApi();
        Call<MovieDbResponse> call = api.getPopularMovies(application.getApplicationContext().getString(R.string.api_key), 1);
        call.enqueue(new Callback<MovieDbResponse>() {
            @Override
            public void onResponse(Call<MovieDbResponse> call, Response<MovieDbResponse> response) {
                MovieDbResponse movieDBResponse = response.body();
                if (movieDBResponse != null && movieDBResponse.getMovies() != null) {
                    List<Movie> movieList = movieDBResponse.getMovies();
                    callback.onResult(movieList, null, (long) 2);
                }
            }

            @Override
            public void onFailure(Call<MovieDbResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Movie> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Movie> callback) {
        api = RetrofitInstance.getApi();

        // here params.key will be the page number. it will increase automatically
        Call<MovieDbResponse> call = api.getPopularMovies(application.getApplicationContext().getString(R.string.api_key), params.key);

        call.enqueue(new Callback<MovieDbResponse>() {
            @Override
            public void onResponse(Call<MovieDbResponse> call, Response<MovieDbResponse> response) {
                MovieDbResponse movieDBResponse = response.body();
                if (movieDBResponse != null && movieDBResponse.getMovies() != null) {
                    List<Movie> movieList = movieDBResponse.getMovies();
                    callback.onResult(movieList, params.key + 1);
                }
            }

            @Override
            public void onFailure(Call<MovieDbResponse> call, Throwable t) {

            }
        });
    }
}
