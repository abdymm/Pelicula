package com.abdymalikmulky.peliculaapp.util;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 5/24/17.
 */

public class EndpointUtil {

    public static final String API_KEY = "44f757f3ac758e2a2617b8f9bc18b862";

    public static final String BASE_URL = "http://api.themoviedb.org/3/";

//    public static final String URL_GET_POPULAR_MOVIES = BASE_URL+"discover/movie?language=en&sort_by=popularity.desc&api_key=" + API_KEY;
//    public static final String URL_GET_HIGHEST_RATED_MOVIES = BASE_URL+"discover/movie?vote_count.gte=500&language=en&sort_by=vote_average.desc&api_key=" + API_KEY;

    public static final String URL_GET_MOVIES = BASE_URL+"discover/movie?language=en&api_key=" + API_KEY;

    public static final String URL_GET_TRAILERS = BASE_URL+"movie/%s/videos?api_key=" + API_KEY;
    public static final String URL_GET_REVIEWS = BASE_URL+"movie/%s/reviews?api_key=" + API_KEY;

    public static final String POSTER_PATH = "http://image.tmdb.org/t/p/w342";
    public static final String BACKDROP_PATH = "http://image.tmdb.org/t/p/w780";


}
