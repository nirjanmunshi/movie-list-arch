package com.androidmadeeasy.movielistarch.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.androidmadeeasy.movielistarch.api.MovieApi;
import com.androidmadeeasy.movielistarch.api.RetrofitInstance;
import com.androidmadeeasy.movielistarch.model.Movie;
import com.androidmadeeasy.movielistarch.model.MovieDataSourceFactory;
import com.androidmadeeasy.movielistarch.repo.MovieDataSource;
import com.androidmadeeasy.movielistarch.repo.MovieRepo;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivityViewModel extends AndroidViewModel {
    private MovieRepo repo;
    private LiveData<MovieDataSource> movieDataSourceLiveData;
    private Executor executor;
    private LiveData<PagedList<Movie>> pagedListLiveData;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        repo = new MovieRepo(application);

        MovieApi api = RetrofitInstance.getApi();
        MovieDataSourceFactory factory = new MovieDataSourceFactory(api,application);
        movieDataSourceLiveData = factory.getMutableLiveData();

        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(20)
                .setPrefetchDistance(4)
                .build();

        executor = Executors.newFixedThreadPool(5);
        pagedListLiveData = new LivePagedListBuilder<Long,Movie>(factory,config)
                .setFetchExecutor(executor)
                .build();

    }

    public LiveData<List<Movie>> getMovieList(){
        return repo.getMovieList();
    }

    public LiveData<PagedList<Movie>> getPagedListLiveData() {
        return pagedListLiveData;
    }
}
