package com.abdymalikmulky.peliculaapp.app.ui.movie.detail.reviews;

import com.abdymalikmulky.peliculaapp.app.BasePresenter;
import com.abdymalikmulky.peliculaapp.app.BaseView;
import com.abdymalikmulky.peliculaapp.app.data.review.Review;

import java.util.List;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 7/8/17.
 */

public class ReviewContract {

    public interface View extends BaseView<Presenter> {
        void showReviews(List<Review> reviews);
        void showError(String msg);

        void onClicked(Review review);
    }

    public interface Presenter extends BasePresenter {
        void loadReviews(String movieId);
    }

}
