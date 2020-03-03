package com.androidmadeeasy.movielistarch.view;

import android.content.Intent;
import android.os.Bundle;

import com.androidmadeeasy.movielistarch.R;
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
    private ImageView movieImage;
    private String image;
    private TextView movieTitle, movieSynopsis, movieRating, movieReleaseDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        movieImage = findViewById(R.id.ivMovieLarge);
        movieTitle =  findViewById(R.id.tvMovieTitle);
        movieSynopsis = findViewById(R.id.tvPlotsynopsis);
        movieRating =  findViewById(R.id.tvMovieRating);
        movieReleaseDate = findViewById(R.id.tvReleaseDate);

        Intent intent = getIntent();
        if (intent.hasExtra("movie")) {

            movie = getIntent().getParcelableExtra("movie");

            Toast.makeText(getApplicationContext(), movie.getOriginalTitle(), Toast.LENGTH_LONG).show();

            image = movie.getPosterPath();

            String path = "https://image.tmdb.org/t/p/w500" + image;

            Glide.with(this)
                    .load(path)
                    .placeholder(R.drawable.loading)
                    .into(movieImage);

            getSupportActionBar().setTitle(movie.getTitle());

            movieTitle.setText(movie.getTitle());
            movieSynopsis.setText(movie.getOverview());
            movieRating.setText(Double.toString(movie.getVoteAverage()));
            movieReleaseDate.setText(movie.getReleaseDate());
        }

    }

}
