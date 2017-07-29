package com.abdymalikmulky.perfilman.app.data.favorite;

import com.abdymalikmulky.perfilman.app.data.movie.Movie;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 7/30/17.
 */

public class FavoriteRepo implements FavoriteDataSource {

    private FavoriteLocal favoriteLocal;
    private FavoriteRemote favoriteRemote;

    public FavoriteRepo(FavoriteLocal favoriteLocal, FavoriteRemote favoriteRemote) {
        this.favoriteLocal = favoriteLocal;
        this.favoriteRemote = favoriteRemote;
    }

    @Override
    public void favorite(Movie movie, FavoriteCallback callback) {
        favoriteLocal.favorite(movie, callback);
    }

    @Override
    public void unFavorite(long _id, UnfavoriteCallback callback) {
        favoriteLocal.unFavorite(_id, callback);
    }

    @Override
    public Favorite isFavorite(String movieId) {
        return favoriteLocal.isFavorite(movieId);
    }
}
