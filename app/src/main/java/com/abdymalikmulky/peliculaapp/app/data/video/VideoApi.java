package com.abdymalikmulky.peliculaapp.app.data.video;

import com.abdymalikmulky.peliculaapp.app.data.video.response.MovieVideoResponse;
import com.abdymalikmulky.peliculaapp.util.EndpointUtil;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 5/2/17.
 */

public interface VideoApi {

    @GET(EndpointUtil.URL_GET_VIDEOS)
    public Call<MovieVideoResponse> getAll(@Path("movie_id") String movieId);


}
