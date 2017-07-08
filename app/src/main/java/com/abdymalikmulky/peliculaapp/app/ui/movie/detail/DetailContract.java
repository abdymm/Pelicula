package com.abdymalikmulky.peliculaapp.app.ui.movie.detail;

import com.abdymalikmulky.peliculaapp.app.BasePresenter;
import com.abdymalikmulky.peliculaapp.app.BaseView;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 7/8/17.
 */

public class DetailContract {

    interface View extends BaseView<Presenter> {
        void showMovie();
        void showError(String msg);
    }

    interface Presenter extends BasePresenter {
    }

}
