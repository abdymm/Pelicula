package com.abdymalikmulky.perfilman.app.data.favorite;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 7/30/17.
 */

public class Favorite {

    long id;
    String movieId;
    String movieTitle;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    @Override
    public String toString() {
        return "Favorite{" +
                "id=" + id +
                ", movieId='" + movieId + '\'' +
                ", movieTitle='" + movieTitle + '\'' +
                '}';
    }
}
