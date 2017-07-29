package com.abdymalikmulky.perfilman.app.data.favorite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.abdymalikmulky.perfilman.app.data.movie.Movie;
import com.abdymalikmulky.perfilman.helper.DatabaseHelper;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 7/30/17.
 */

public class FavoriteLocal extends DatabaseHelper implements FavoriteDataSource {

    private Context context;

    public FavoriteLocal(Context context) {
        super(context);
        this.context = context;
        open();
    }

    @Override
    public void favorite(Movie movie, FavoriteCallback callback) {
        ContentValues contentValues = objectToContentValues(movie);

        Uri uri = context.getContentResolver().insert(FavoriteContract.FavoriteEntry.CONTENT_URI, contentValues);
        if(uri != null) {
            String[] uriSplit = uri.toString().split("/");
            String idString = uriSplit[(uriSplit.length) - 1];
            long _id = Long.parseLong(idString);


            Favorite favoriteMovie = new Favorite();
            favoriteMovie.setId(_id);
            favoriteMovie.setMovieId(movie.getId());
            favoriteMovie.setMovieTitle(movie.getTitle());

            callback.onFavorited(favoriteMovie);
        } else {
            callback.onFailed("Failed save");
        }

    }

    @Override
    public void unFavorite(long _id, UnfavoriteCallback callback) {
        String stringId = Long.toString(_id);
        Uri uri = FavoriteContract.FavoriteEntry.CONTENT_URI;
        uri = uri.buildUpon().appendPath(stringId).build();
        int deleted = context.getContentResolver().delete(uri, null, null);
        if(deleted > 0) {
            callback.onUnfavorited();
        } else {
            callback.onFailed("Fail to Delete");
        }
    }

    @Override
    public Favorite isFavorite(String movieId) {
        Cursor cursor = context.getContentResolver().query(FavoriteContract.FavoriteEntry.CONTENT_URI,
                null,
                FavoriteContract.FavoriteEntry.COLUMN_MOVIE_ID + " = ?",
                new String[]{movieId},
                null);

        cursor.moveToFirst();
        if(cursor.getCount() > 0) {
            Favorite favorite = cursorToItem(cursor);
            return favorite;
        }
        //null its mean not favorited movies
        return null;
    }

    @Override
    public Favorite cursorToItem(Cursor cursor) {
        Favorite favorite = new Favorite();
        favorite.setId(cursor.getLong(0));
        favorite.setMovieId(cursor.getString(1));
        favorite.setMovieTitle(cursor.getString(2));
        return favorite;
    }

    public ContentValues objectToContentValues(Movie movie) {
        ContentValues values = new ContentValues();
        values.put(FavoriteContract.FavoriteEntry.COLUMN_MOVIE_ID, movie.getId());
        values.put(FavoriteContract.FavoriteEntry.COLUMN_MOVIE_TITLE, movie.getTitle());

        return values;
    }
}
