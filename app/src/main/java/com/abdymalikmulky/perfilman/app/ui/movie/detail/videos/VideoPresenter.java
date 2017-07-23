package com.abdymalikmulky.perfilman.app.ui.movie.detail.videos;

import com.abdymalikmulky.perfilman.app.data.video.Video;
import com.abdymalikmulky.perfilman.app.data.video.VideoDataSource;
import com.abdymalikmulky.perfilman.app.data.video.VideoRepo;

import java.util.List;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 7/23/17.
 */

public class VideoPresenter implements VideoContract.Presenter {

    private VideoRepo videoRepo;
    private VideoContract.View videoView;

    public VideoPresenter(VideoRepo videoRepo, VideoContract.View videoView) {
        this.videoRepo = videoRepo;
        this.videoView = videoView;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void loadTrailers(String movieId) {
        videoRepo.load(movieId, new VideoDataSource.LoadVideosCallback() {
            @Override
            public void onLoaded(List<Video> videos) {
                videoView.showVideos(videos);
            }

            @Override
            public void onFailed(String errorMessage) {
                videoView.showError(errorMessage);
            }
        });
    }
}
