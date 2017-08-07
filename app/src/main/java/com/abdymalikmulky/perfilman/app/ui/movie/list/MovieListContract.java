package com.abdymalikmulky.perfilman.app.ui.movie.list;

import com.abdymalikmulky.perfilman.app.BasePresenter;
import com.abdymalikmulky.perfilman.app.BaseView;
import com.abdymalikmulky.perfilman.app.data.movie.Movie;

import java.util.List;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 7/7/17.
 */

public class MovieListContract {

    interface View extends BaseView<Presenter> {
        void showMovies(List<Movie> movies);

        void showLoadMoreMovies(List<Movie> movies);

        void showError(String msg);

        void showErrorLoadMore(String msg);

        void onListClicked(Movie movie);

    }

    interface Presenter extends BasePresenter {
        void loadMovies(String filter);

        void loadMoreMovies(int page, String filter);
    }

}
