package com.abdymalikmulky.perfilman.app.data.favorite;

import com.abdymalikmulky.perfilman.app.data.movie.Movie;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 7/30/17.
 */

/**
 * For now its not implemented yet
 */
public class FavoriteRemote implements FavoriteDataSource {
    @Override
    public void favorite(Movie movie, FavoriteCallback callback) {

    }

    @Override
    public void unFavorite(long _id, UnfavoriteCallback callback) {

    }

    @Override
    public Favorite isFavorite(String movieId) {
        return null;
    }
}
