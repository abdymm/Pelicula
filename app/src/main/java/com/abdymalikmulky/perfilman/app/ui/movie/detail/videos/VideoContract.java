package com.abdymalikmulky.perfilman.app.ui.movie.detail.videos;

import com.abdymalikmulky.perfilman.app.BasePresenter;
import com.abdymalikmulky.perfilman.app.BaseView;
import com.abdymalikmulky.perfilman.app.data.video.Video;

import java.util.List;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 7/8/17.
 */

public class VideoContract {

    public interface View extends BaseView<Presenter> {
        void showVideos(List<Video> videos);
        void showError(String msg);

        void onClicked(Video video);
    }

    public interface Presenter extends BasePresenter {
        void loadTrailers(String movieId);
    }

}
