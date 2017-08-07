package com.abdymalikmulky.perfilman.app.data.movie;

import java.util.List;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 7/7/17.
 */

public interface MovieDataSource {

    interface LoadMoviesCallback {
        void onLoaded(List<Movie> movies);
        void onFailed(String errorMessage);
    }

    void load(int page, String filter, LoadMoviesCallback callback);
}
