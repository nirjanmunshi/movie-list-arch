package com.androidmadeeasy.movielistarch.view;

import android.content.Intent;
import android.os.Bundle;

import com.androidmadeeasy.movielistarch.R;
import com.androidmadeeasy.movielistarch.databinding.ActivityMovieBinding;
import com.androidmadeeasy.movielistarch.model.Movie;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MovieActivity extends AppCompatActivity {

    private Movie movie;
    private ActivityMovieBinding activityMovieBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMovieBinding = ActivityMovieBinding.inflate(getLayoutInflater());
        View v = activityMovieBinding.getRoot();
        setContentView(v);
        Toolbar toolbar = activityMovieBinding.toolbar;
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        if (intent.hasExtra("movie")) {
            movie = getIntent().getParcelableExtra("movie");
            setTitle(movie.getTitle());
            activityMovieBinding.setMovie(movie);
        }
    }
}
