package com.abdymalikmulky.perfilman.app.data.favorite;

import com.abdymalikmulky.perfilman.app.data.movie.Movie;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 7/30/17.
 */

public interface FavoriteDataSource {
    interface FavoriteCallback {
        void onFavorited(Favorite favoriteMovie);
        void onFailed(String errorMessage);
    }
    interface UnfavoriteCallback {
        void onUnfavorited();
        void onFailed(String errorMessage);
    }

    void favorite(Movie movie, FavoriteCallback callback);

    void unFavorite(long _id, UnfavoriteCallback callback);

    Favorite isFavorite(String movieId);
}
