package com.abdymalikmulky.perfilman.app.data.movie;

import android.content.Context;

import com.abdymalikmulky.perfilman.util.NetworkUtil;

import java.util.List;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 7/7/17.
 */

public class MovieRepo implements MovieDataSource {

    private Context context;
    private MovieLocal movieLocal;
    private MovieRemote movieRemote;

    public MovieRepo(Context context, MovieLocal movieLocal, MovieRemote movieRemote) {
        this.context = context;

        this.movieLocal = movieLocal;
        this.movieRemote = movieRemote;
    }

    @Override
    public void load(String filter, final LoadMoviesCallback callback) {
        //Check if network is available
        if(NetworkUtil.isNetworkAvailable(context)) {
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
        } else {
            movieLocal.load(filter, new LoadMoviesCallback() {
                @Override
                public void onLoaded(List<Movie> movies) {
                    callback.onLoaded(movies);
                }

                @Override
                public void onFailed(String errorMessage) {
                    callback.onFailed(errorMessage);
                }
            });
        }

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
