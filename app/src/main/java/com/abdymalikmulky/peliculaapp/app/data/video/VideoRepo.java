package com.abdymalikmulky.peliculaapp.app.data.video;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 7/23/17.
 */

public class VideoRepo implements VideoDataSource {

    private VideoLocal videoLocal;
    private VideoRemote videoRemote;

    public VideoRepo(VideoLocal videoLocal, VideoRemote videoRemote) {
        this.videoLocal = videoLocal;
        this.videoRemote = videoRemote;
    }

    @Override
    public void load(String movieId, LoadVideosCallback callback) {
        videoRemote.load(movieId, callback);
    }
}
