package com.abdymalikmulky.peliculaapp.app.data.movie;

import com.abdymalikmulky.peliculaapp.util.ConstantsUtil;
import com.raizlabs.android.dbflow.sql.language.From;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.Where;

import java.util.List;

import static com.raizlabs.android.dbflow.sql.language.SQLite.select;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 7/7/17.
 */

public class MovieLocal implements MovieDataSource {


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


}
