package com.abdymalikmulky.perfilman.app.data.review;

import com.abdymalikmulky.perfilman.app.data.review.response.MovieReviewResponse;
import com.abdymalikmulky.perfilman.helper.ApiHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 7/23/17.
 */

public class ReviewRemote implements ReviewDataSource {

    ReviewApi api;

    public ReviewRemote() {
        api = ApiHelper.client().create(ReviewApi.class);
    }

    @Override
    public void load(String movieId, final LoadReviewsCallback callback) {
        Call<MovieReviewResponse> call = api.getAll(movieId);
        call.enqueue(new Callback<MovieReviewResponse>() {
            @Override
            public void onResponse(Call<MovieReviewResponse> call, Response<MovieReviewResponse> response) {
                if(response.isSuccessful()) {
                    MovieReviewResponse movieReviewResponse = response.body();
                    List<Review> reviews = response.body().getReviews();
                    if(reviews.size() > 0) {
                        callback.onLoaded(reviews);
                    } else {
                        callback.onFailed("NO DATA REVIEWS");
                    }
                } else {
                    callback.onFailed(response.message());
                }
            }

            @Override
            public void onFailure(Call<MovieReviewResponse> call, Throwable t) {
                callback.onFailed(t.toString());
            }
        });
    }
}
