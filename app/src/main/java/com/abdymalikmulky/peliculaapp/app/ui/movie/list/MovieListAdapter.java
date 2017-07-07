package com.abdymalikmulky.peliculaapp.app.ui.movie.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abdymalikmulky.peliculaapp.R;
import com.abdymalikmulky.peliculaapp.app.data.movie.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 7/7/17.
 */

class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {

    private List<Movie> movies;
    private Context context;
    private MovieListContract.View movieListView;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.movie_poster)
        ImageView moviePoster;
        @BindView(R.id.title_background)
        View titleBackground;
        @BindView(R.id.movie_name)
        TextView movieName;

        public Movie movie;

        public ViewHolder(View root) {
            super(root);
            ButterKnife.bind(this, root);
        }

        @Override
        public void onClick(View view) {

        }
    }

    public MovieListAdapter(List<Movie> movies, MovieListContract.View moviesView) {
        this.movies = movies;
        movieListView = moviesView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View rootView = LayoutInflater.from(context).inflate(R.layout.grid_item_movie, parent, false);

        return new ViewHolder(rootView);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(holder);

        holder.movie = movies.get(position);

        holder.movieName.setText(holder.movie.getTitle());

        Picasso.with(context)
                .load(holder.movie.getPosterPath())
                .placeholder(R.drawable.blank_movie_poster)
                .error(R.drawable.blank_movie_poster)
                .into(holder.moviePoster);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void refresh(List<Movie> movies) {
        this.movies.clear();
        this.movies = movies;
        notifyDataSetChanged();
    }
}
