package com.androidmadeeasy.movielistarch.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.androidmadeeasy.movielistarch.api.MovieApi;
import com.androidmadeeasy.movielistarch.repo.MovieDataSource;

public class MovieDataSourceFactory extends DataSource.Factory {

    private MovieDataSource movieDataSource;
    private MovieApi movieApi;
    private Application application;
    private MutableLiveData<MovieDataSource> mutableLiveData;

    public MovieDataSourceFactory(MovieApi movieApi, Application application) {
        this.movieApi = movieApi;
        this.application = application;
        mutableLiveData = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public DataSource create() {
        movieDataSource = new MovieDataSource(movieApi,application);
        mutableLiveData.postValue(movieDataSource);
        return movieDataSource;
    }

    public MutableLiveData<MovieDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}
