package com.abdymalikmulky.perfilman.app.ui.movie.detail;

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
    private DetailContract.View detailView;

    public DetailPresenter(VideoRepo videoRepo, DetailContract.View detailView) {
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
}
