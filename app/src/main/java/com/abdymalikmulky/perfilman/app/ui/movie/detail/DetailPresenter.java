package com.abdymalikmulky.perfilman.app.ui.movie.detail;

import com.abdymalikmulky.perfilman.app.data.favorite.Favorite;
import com.abdymalikmulky.perfilman.app.data.favorite.FavoriteDataSource;
import com.abdymalikmulky.perfilman.app.data.favorite.FavoriteRepo;
import com.abdymalikmulky.perfilman.app.data.movie.Movie;
import com.abdymalikmulky.perfilman.app.data.video.Video;
import com.abdymalikmulky.perfilman.app.data.video.VideoDataSource;
import com.abdymalikmulky.perfilman.app.data.video.VideoRepo;

import java.util.List;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 7/8/17.
 */

public class DetailPresenter implements DetailContract.Presenter {

    private VideoRepo videoRepo;
    private FavoriteRepo favoriteRepo;

    private DetailContract.View detailView;

    public DetailPresenter(FavoriteRepo favoriteRepo, VideoRepo videoRepo, DetailContract.View detailView) {
        this.favoriteRepo = favoriteRepo;
        this.videoRepo = videoRepo;
        this.detailView = detailView;
        this.detailView.setPresenter(this);
    }

    @Override
    public void start() {
        detailView.showMovie();
    }

    @Override
    public void stop() {

    }

    @Override
    public void loadFirstVideo(String movieId) {
        videoRepo.load(movieId, new VideoDataSource.LoadVideosCallback() {
            @Override
            public void onLoaded(List<Video> videos) {
                detailView.showFirstVideoInToolbar(videos.get(0));
            }

            @Override
            public void onFailed(String errorMessage) {
                detailView.showError(errorMessage);
            }
        });
    }

    @Override
    public void updateToFavorite(Movie movie) {
        favoriteRepo.favorite(movie, new FavoriteDataSource.FavoriteCallback() {
            @Override
            public void onFavorited(Favorite favoriteMovie) {
                detailView.showFavorited(favoriteMovie);
            }

            @Override
            public void onFailed(String errorMessage) {
                detailView.showError(errorMessage);
            }
        });
    }

    @Override
    public void updateToUnfavorite(long _id) {
        favoriteRepo.unFavorite(_id, new FavoriteDataSource.UnfavoriteCallback() {
            @Override
            public void onUnfavorited() {
                detailView.showUnfavorited();
            }

            @Override
            public void onFailed(String errorMessage) {
                detailView.showError(errorMessage);
            }
        });
    }

    @Override
    public void isFavorited(String movieId) {
        Favorite favoriteMovie = favoriteRepo.isFavorite(movieId);
        if(favoriteMovie != null)  {
            detailView.showFavorited(favoriteMovie);
        } else {
            detailView.showUnfavorited();
        }
    }
}
