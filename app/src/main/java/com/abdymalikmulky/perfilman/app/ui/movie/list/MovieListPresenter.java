package com.abdymalikmulky.perfilman.app.ui.movie.list;

import com.abdymalikmulky.perfilman.app.data.movie.Movie;
import com.abdymalikmulky.perfilman.app.data.movie.MovieDataSource;
import com.abdymalikmulky.perfilman.app.data.movie.MovieRepo;

import java.util.List;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 7/7/17.
 */

public class MovieListPresenter implements MovieListContract.Presenter {

    MovieListContract.View movieListView;
    MovieRepo movieRepo;

    public MovieListPresenter(MovieListContract.View movieListView, MovieRepo movieRepo) {
        this.movieListView = movieListView;
        this.movieRepo = movieRepo;

        this.movieListView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void loadMovies(String sortBy) {
        movieRepo.load(sortBy, new MovieDataSource.LoadMoviesCallback(){
            @Override
            public void onLoaded(List<Movie> movies) {
                movieListView.showMovies(movies);
            }

            @Override
            public void onFailed(String errorMessage) {
                movieListView.showError(errorMessage);
            }
        });
    }
}
