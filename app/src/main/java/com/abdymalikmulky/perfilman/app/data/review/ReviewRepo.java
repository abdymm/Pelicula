package com.abdymalikmulky.perfilman.app.data.review;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 7/23/17.
 */

public class ReviewRepo implements ReviewDataSource {

    private ReviewLocal reviewLocal;
    private ReviewRemote reviewRemote;

    public ReviewRepo(ReviewLocal reviewLocal, ReviewRemote reviewRemote) {
        this.reviewLocal = reviewLocal;
        this.reviewRemote = reviewRemote;
    }

    @Override
    public void load(String movieId, LoadReviewsCallback callback) {
        reviewRemote.load(movieId, callback);
    }
}
