package com.abdymalikmulky.perfilman.app.ui.movie.detail;

import com.abdymalikmulky.perfilman.app.BasePresenter;
import com.abdymalikmulky.perfilman.app.BaseView;
import com.abdymalikmulky.perfilman.app.data.favorite.Favorite;
import com.abdymalikmulky.perfilman.app.data.movie.Movie;
import com.abdymalikmulky.perfilman.app.data.video.Video;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 7/8/17.
 */

public class DetailContract {

    public interface View extends BaseView<Presenter> {
        void showMovie();

        void showError(String msg);

        void showFirstVideoInToolbar(Video video);

        void showFavorited(Favorite favoriteMovie);

        void showUnfavorited();
    }

    public interface Presenter extends BasePresenter {
        void loadFirstVideo(String movieId);

        void updateToFavorite(Movie movie);

        void updateToUnfavorite(long _id);

        void isFavorited(String movieId);
    }

}
