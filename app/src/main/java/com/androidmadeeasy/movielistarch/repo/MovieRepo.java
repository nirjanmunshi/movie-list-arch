package com.androidmadeeasy.movielistarch.repo;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.androidmadeeasy.movielistarch.R;
import com.androidmadeeasy.movielistarch.api.MovieApi;
import com.androidmadeeasy.movielistarch.api.RetrofitInstance;
import com.androidmadeeasy.movielistarch.model.Movie;
import com.androidmadeeasy.movielistarch.model.MovieDbResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepo {
    private List<Movie> movieList = new ArrayList<>();
    private MutableLiveData<List<Movie>> listMutableLiveData = new MutableLiveData<>();
    private Application application;

    public MovieRepo(Application application) {
        this.application = application;
    }

    public LiveData<List<Movie>> getMovieList(){
        MovieApi api = RetrofitInstance.getApi();
        Call<MovieDbResponse> call = api.getPopularMovies(application.getApplicationContext().getString(R.string.api_key));

        call.enqueue(new Callback<MovieDbResponse>() {
            @Override
            public void onResponse(Call<MovieDbResponse> call, Response<MovieDbResponse> response) {
                MovieDbResponse movieDBResponse = response.body();
                if (movieDBResponse != null && movieDBResponse.getMovies() != null) {
                    movieList = movieDBResponse.getMovies();
                    listMutableLiveData.setValue(movieList);
                }
            }

            @Override
            public void onFailure(Call<MovieDbResponse> call, Throwable t) {

            }
        });
        return listMutableLiveData;
    }
}
