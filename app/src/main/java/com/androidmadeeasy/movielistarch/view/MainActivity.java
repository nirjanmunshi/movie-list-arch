package com.androidmadeeasy.movielistarch.view;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.androidmadeeasy.movielistarch.R;
import com.androidmadeeasy.movielistarch.adapter.MovieAdapter;
import com.androidmadeeasy.movielistarch.api.MovieApi;
import com.androidmadeeasy.movielistarch.api.RetrofitInstance;
import com.androidmadeeasy.movielistarch.model.Movie;
import com.androidmadeeasy.movielistarch.model.MovieDbResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<Movie> movies;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = findViewById(R.id.swipe_layout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(() -> getPopularMovies());

        setTitle("TMDB Popular Movies Today");

        getPopularMovies();
    }

    public void getPopularMovies() {
        MovieApi api = RetrofitInstance.getApi();
        Call<MovieDbResponse> call = api.getPopularMovies(this.getString(R.string.api_key));

        call.enqueue(new Callback<MovieDbResponse>() {
            @Override
            public void onResponse(Call<MovieDbResponse> call, Response<MovieDbResponse> response) {
                MovieDbResponse movieDBResponse = response.body();
                if (movieDBResponse != null && movieDBResponse.getMovies() != null) {
                    movies = movieDBResponse.getMovies();
                    showOnRecyclerView();
                }
            }

            @Override
            public void onFailure(Call<MovieDbResponse> call, Throwable t) {

            }
        });

    }

    private void showOnRecyclerView() {
        recyclerView = findViewById(R.id.rvMovies);
        movieAdapter = new MovieAdapter(this, movies);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();

    }
}
