package com.abdymalikmulky.peliculaapp.app.data.movie;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import static com.raizlabs.android.dbflow.sql.language.SQLite.select;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 7/7/17.
 */

public class MovieLocal implements MovieDataSource {


    @Override
    public void load(String filter, LoadMoviesCallback callback) {
        List<Movie> movies =  select()
                .from(Movie.class)
                .queryList();
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
                .where(Movie_Table.id.eq("$"))
                .count();
        return (rowCount > 0) ? true : false;
    }

    public void delete(){
        SQLite.delete(Movie.class)
                .execute();
    }


}
