package com.androidmadeeasy.movielistarch.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidmadeeasy.movielistarch.view.MovieActivity;
import com.androidmadeeasy.movielistarch.R;
import com.androidmadeeasy.movielistarch.model.Movie;
import com.bumptech.glide.Glide;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private Context context;
    private List<Movie> movieArrayList;

    public MovieAdapter(Context context, List<Movie> movieArrayList) {
        this.context = context;
        this.movieArrayList = movieArrayList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        holder.movieTitle.setText(movieArrayList.get(position).getOriginalTitle());
        holder.rate.setText(Double.toString(movieArrayList.get(position).getVoteAverage()));

        String imagePath = "https://image.tmdb.org/t/p/w500" + movieArrayList.get(position).getPosterPath();

        Glide.with(context)
                .load(imagePath)
                .placeholder(R.drawable.loading)
                .into(holder.movieImage);
    }

    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }


    class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView movieTitle, rate;
        ImageView movieImage;
        MovieViewHolder(View itemView) {
            super(itemView);

            movieImage = itemView.findViewById(R.id.ivMovie);
            rate = itemView.findViewById(R.id.tvRating);
            movieTitle = itemView.findViewById(R.id.tvTitle);

            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Movie selectedMovie = movieArrayList.get(position);
                    Intent intent = new Intent(context, MovieActivity.class);
                    intent.putExtra("movie", selectedMovie);
                    context.startActivity(intent);
                }
            });
        }
    }
}
