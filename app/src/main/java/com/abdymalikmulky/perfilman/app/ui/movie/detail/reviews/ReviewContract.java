package com.abdymalikmulky.perfilman.app.ui.movie.detail.reviews;

import com.abdymalikmulky.perfilman.app.BasePresenter;
import com.abdymalikmulky.perfilman.app.BaseView;
import com.abdymalikmulky.perfilman.app.data.review.Review;

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
