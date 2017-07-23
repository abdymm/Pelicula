package com.abdymalikmulky.peliculaapp.app.data.review;

import com.abdymalikmulky.peliculaapp.app.data.review.response.MovieReviewResponse;
import com.abdymalikmulky.peliculaapp.util.EndpointUtil;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 5/2/17.
 */

public interface ReviewApi {

    @GET(EndpointUtil.URL_GET_REVIEWS)
    public Call<MovieReviewResponse> getAll(@Path("movie_id") String movieId);


}
