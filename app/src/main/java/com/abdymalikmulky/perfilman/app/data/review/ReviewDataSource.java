package com.abdymalikmulky.perfilman.app.data.review;

import java.util.List;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 7/23/17.
 */

public interface ReviewDataSource {
    interface LoadReviewsCallback {
        void onLoaded(List<Review> reviews);
        void onFailed(String errorMessage);
    }

    void load(String movieId, LoadReviewsCallback callback);
}
