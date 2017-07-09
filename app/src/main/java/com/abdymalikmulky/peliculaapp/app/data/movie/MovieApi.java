package com.abdymalikmulky.peliculaapp.app.data.movie;

import com.abdymalikmulky.peliculaapp.app.data.movie.response.DiscoverMovieResponse;
import com.abdymalikmulky.peliculaapp.util.EndpointUtil;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 5/2/17.
 */

public interface MovieApi {

    @GET(EndpointUtil.URL_GET_MOVIES)
    public Call<DiscoverMovieResponse> getAll(@Path("sort_by") String sortBy,  @Query("page") int page);


}
