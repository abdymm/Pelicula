package com.abdymalikmulky.perfilman.app.data.video;

import java.util.List;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 7/23/17.
 */

public interface VideoDataSource {
    interface LoadVideosCallback {
        void onLoaded(List<Video> videos);
        void onFailed(String errorMessage);
    }

    void load(String movieId, LoadVideosCallback callback);
}
