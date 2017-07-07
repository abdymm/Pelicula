package com.abdymalikmulky.peliculaapp.app.data.movie;

import java.util.List;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 7/7/17.
 */

public class MovieRepo implements MovieDataSource {

    private MovieLocal movieLocal;
    private MovieRemote movieRemote;

    public MovieRepo(MovieLocal movieLocal, MovieRemote movieRemote) {
        this.movieLocal = movieLocal;
        this.movieRemote = movieRemote;
    }

    @Override
    public void load(String filter, final LoadMoviesCallback callback) {
        movieRemote.load(filter, new LoadMoviesCallback() {
            @Override
            public void onLoaded(List<Movie> movies) {
                saveMovieOnLocal(movies);
                callback.onLoaded(movies);
            }

            @Override
            public void onFailed(String errorMessage) {
                callback.onFailed(errorMessage);
            }
        });
    }

    private void saveMovieOnLocal(List<Movie> movies) {
        for (Movie movie : movies) {
            //if exist on db local, dont save
            if(!movieLocal.isExist(movie.getId())) {

                movieLocal.save(movie);

            }
        }
    }
}
