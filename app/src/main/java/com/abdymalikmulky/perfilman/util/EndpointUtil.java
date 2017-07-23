package com.abdymalikmulky.perfilman.util;

import com.abdymalikmulky.perfilman.BuildConfig;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 5/24/17.
 */

public class EndpointUtil {

    public static final String API_KEY = BuildConfig.MOVIE_DB_API_TOKEN;

    public static final String BASE_URL = "http://api.themoviedb.org/3/";

    public static final String URL_GET_MOVIES = BASE_URL+"movie/{sort_by}?language=en-US&api_key=" + API_KEY;

    public static final String POSTER_PATH = "http://image.tmdb.org/t/p/w185";
    public static final String BACKDROP_PATH = "http://image.tmdb.org/t/p/w780";

    //TRAILER
    public static final String URL_GET_VIDEOS = "http://api.themoviedb.org/3/movie/{movie_id}/videos?api_key=" + API_KEY;
    public static final String YOUTUBE_TRAILER_URL = "http://www.youtube.com/watch?v=%1$s";
    public static final String YOUTUBE_THUMBNAIL_URL = "http://img.youtube.com/vi/%1$s/0.jpg";

    //REVIEW
    public static final String URL_GET_REVIEWS = "http://api.themoviedb.org/3/movie/{movie_id}/reviews?api_key=" + API_KEY;

}
