package com.androidmadeeasy.movielistarch.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.androidmadeeasy.movielistarch.model.Movie;
import com.androidmadeeasy.movielistarch.repo.MovieRepo;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    private MovieRepo repo;
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        repo = new MovieRepo(application);
    }

    public LiveData<List<Movie>> getMovieList(){
        return repo.getMovieList();
    }
}
