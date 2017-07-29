package com.abdymalikmulky.perfilman.app.data.movie;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.abdymalikmulky.perfilman.app.data.favorite.FavoriteContract;
import com.abdymalikmulky.perfilman.helper.DatabaseHelper;
import com.abdymalikmulky.perfilman.util.ConstantsUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 7/7/17.
 */

public class MovieLocal extends DatabaseHelper implements MovieDataSource{

    //table
    public static final String TABLE_MOVIE = "movie";
    public static String thisTable = TABLE_MOVIE;
    //Column
    public static final String KEY_ID = "id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_OVERVIEW = "overview";
    public static final String KEY_POSTER_PATH = "poster_path";
    public static final String KEY_BACKDROP_PATH = "backdop_path";
    public static final String KEY_RELEASE_DATE = "release_date";
    public static final String KEY_VOTE_COUNT = "vote_count";
    public static final String KEY_VOTE_AVERAGE = "average";
    public static final String KEY_POPULARITY = "popularity";

    public static final String CREATE_TABLE = "CREATE TABLE " + thisTable + "("
            + KEY_ID + " TEXT,"
            + KEY_TITLE + " TEXT,"
            + KEY_OVERVIEW + " INTEGER,"
            + KEY_POSTER_PATH + " TEXT,"
            + KEY_BACKDROP_PATH + " TEXT,"
            + KEY_RELEASE_DATE + " TEXT,"
            + KEY_VOTE_COUNT + " INTEGER,"
            + KEY_VOTE_AVERAGE + " REAL,"
            + KEY_POPULARITY + " REAL" + ")";


    public MovieLocal(Context context) {
        super(context);
        open();
    }

    @Override
    public void load(String filter, LoadMoviesCallback callback) {
        String conditionQuery = "";
        if(filter.equals(ConstantsUtil.MOVIE_LIST_SORT_BY_POPULARITY_DESC)) {
            conditionQuery =  "ORDER BY "+KEY_POPULARITY+" DESC";
        } else if(filter.equals(ConstantsUtil.MOVIE_LIST_SORT_BY_VOTE_AVERAGE)) {
            conditionQuery =  "ORDER BY "+KEY_VOTE_AVERAGE+" DESC";
        } else {
            conditionQuery =  "WHERE " + KEY_ID + " in " +
                    "( select " + FavoriteContract.FavoriteEntry.COLUMN_MOVIE_ID + " " +
                    "from " + FavoriteContract.FavoriteEntry.TABLE_NAME + " )";
        }

        List<Movie> movies = queryAll(conditionQuery);
        if(movies.size() > 0) {
            callback.onLoaded(movies);
        } else {
            callback.onFailed("NO DATA" + thisTable.toUpperCase());
        }
    }

    public boolean isExist(String movieId){
        return queryById(movieId) != null ? true : false;
    }

    public boolean save(Movie movie) {
        ContentValues contentValues = objectToContentValues(movie);
        long insertId = insertAll(thisTable, contentValues);

        return checkQueryDbSuccess(insertId);
    }


    @Override
    public Movie cursorToItem(Cursor cursor) {
        Movie  movie = new Movie();
        movie.setId(cursor.getString(0));
        movie.setTitle(cursor.getString(1));
        movie.setOverview(cursor.getString(2));
        movie.setPosterPath(cursor.getString(3));
        movie.setBackdropPath(cursor.getString(4));
        movie.setReleaseDate(cursor.getString(5));
        movie.setVoteCount(cursor.getInt(6));
        movie.setVoteAverage(cursor.getDouble(7));
        movie.setPopularity(cursor.getDouble(8));

        return movie;
    }

    public ContentValues objectToContentValues(Movie movie) {
        ContentValues values = new ContentValues();
        values.put(KEY_ID, movie.getId());
        values.put(KEY_TITLE, movie.getTitle());
        values.put(KEY_OVERVIEW, movie.getOverview());
        values.put(KEY_POSTER_PATH, movie.getPosterPath());
        values.put(KEY_BACKDROP_PATH, movie.getBackdropPath());
        values.put(KEY_RELEASE_DATE, movie.getReleaseDate());
        values.put(KEY_VOTE_COUNT, movie.getVoteCount());
        values.put(KEY_VOTE_AVERAGE, movie.getVoteAverage());
        values.put(KEY_POPULARITY, movie.getPopularity());

        return values;
    }

    private List<Movie> queryAll(String conditionQuery) {
        List<Movie> movies = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + thisTable + " " + conditionQuery;

        Cursor cursor = getDatabase().rawQuery(selectQuery, null);

        cursor.moveToFirst();
        if(cursor.getCount() > 0) {
            do {
                Movie movie = cursorToItem(cursor);
                movies.add(movie);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return movies;
    }

    private Movie queryById(String movieId) {
        Movie movie = new Movie();
        int rowCount = 0;
        String selectQuery = "SELECT * FROM " + thisTable + " WHERE " + KEY_ID + "= ?";
        Cursor cursor = getDatabase().rawQuery(selectQuery, new String[]{String.valueOf(movieId)});

        cursor.moveToFirst();
        rowCount = cursor.getCount();

        if (rowCount > 0) {
            movie = cursorToItem(cursor);
        }

        cursor.close();

        if (rowCount > 0) {
            return movie;
        }
        return null;
    }

    /*
    @Override
    public void load(String filter, LoadMoviesCallback callback) {
        From<Movie> fromQuery = select()
                .from(Movie.class);

        Where<Movie> whereQuery;

        if(filter.equals(ConstantsUtil.MOVIE_LIST_SORT_BY_POPULARITY_DESC)) {
            whereQuery =  fromQuery
                    .orderBy(Movie_Table.popularity, false);
        } else {
            whereQuery =  fromQuery
                    .orderBy(Movie_Table.voteAverage, false);
        }
        List<Movie> movies = whereQuery.queryList();

        if(movies.size() > 0) {
            callback.onLoaded(movies);
        }else{
            callback.onFailed("No Data");
        }
    }

    public boolean save(Movie movie){
        return (movie.insert() > 0) ? true : false;
    }

    public boolean isExist(String movieId){
        long rowCount = SQLite.select()
                .from(Movie.class)
                .where(Movie_Table.id.eq(movieId))
                .count();
        return (rowCount > 0) ? true : false;
    }

    public void delete(){
        SQLite.delete(Movie.class)
                .execute();
    }
    */


}
