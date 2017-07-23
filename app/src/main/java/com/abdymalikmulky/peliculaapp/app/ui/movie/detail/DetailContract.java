package com.abdymalikmulky.peliculaapp.app.ui.movie.detail;

import com.abdymalikmulky.peliculaapp.app.BasePresenter;
import com.abdymalikmulky.peliculaapp.app.BaseView;
import com.abdymalikmulky.peliculaapp.app.data.video.Video;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 7/8/17.
 */

public class DetailContract {

    public interface View extends BaseView<Presenter> {
        void showMovie();
        void showError(String msg);

        void showFirstVideoInToolbar(Video video);
    }

    public interface Presenter extends BasePresenter {
        void loadFirstVideo(String movieId);
    }

}
