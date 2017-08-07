package com.abdymalikmulky.perfilman.app.ui.movie.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abdymalikmulky.perfilman.R;
import com.abdymalikmulky.perfilman.app.data.movie.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 7/7/17.
 */

class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    private List<Movie> movies;
    private Context context;
    private MovieListContract.View movieListView;


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.movie_detail_backdrop)
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
            movieListView.onListClicked(movie);
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
        Timber.d("MovieListData %s", holder.movie.toString());

        holder.movieName.setText(holder.movie.getTitle());

        Picasso.with(context)
                .load(holder.movie.getFullPosterPath())
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
    public void add(List<Movie> movies){
        this.movies.addAll(movies);
        notifyDataSetChanged();
    }

}
