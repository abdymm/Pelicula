package com.abdymalikmulky.peliculaapp.app.ui.movie.list;

import com.abdymalikmulky.peliculaapp.app.BasePresenter;
import com.abdymalikmulky.peliculaapp.app.BaseView;
import com.abdymalikmulky.peliculaapp.app.data.movie.Movie;

import java.util.List;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 7/7/17.
 */

public class MovieListContract {

    interface View extends BaseView<Presenter> {
        void showMovies(List<Movie> movies);
        void showError(String msg);
    }

    interface Presenter extends BasePresenter {
        void loadMovies(String filter);
    }
}
