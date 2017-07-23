package com.abdymalikmulky.peliculaapp.app.ui.movie.detail.reviews;

import com.abdymalikmulky.peliculaapp.app.data.review.Review;
import com.abdymalikmulky.peliculaapp.app.data.review.ReviewDataSource;
import com.abdymalikmulky.peliculaapp.app.data.review.ReviewRepo;

import java.util.List;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 7/23/17.
 */

public class ReviewPresenter implements ReviewContract.Presenter {

    private ReviewRepo reviewRepo;
    private ReviewContract.View reviewView;

    public ReviewPresenter(ReviewRepo reviewRepo, ReviewContract.View reviewView) {
        this.reviewRepo = reviewRepo;
        this.reviewView = reviewView;
        this.reviewView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void loadReviews(String movieId) {
        reviewRepo.load(movieId, new ReviewDataSource.LoadReviewsCallback() {
            @Override
            public void onLoaded(List<Review> reviews) {
                reviewView.showReviews(reviews);
            }

            @Override
            public void onFailed(String errorMessage) {
                reviewView.showError(errorMessage);
            }
        });
    }
}
