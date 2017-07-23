package com.abdymalikmulky.perfilman.app.data.video;

import com.abdymalikmulky.perfilman.app.data.video.response.MovieVideoResponse;
import com.abdymalikmulky.perfilman.helper.ApiHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 7/23/17.
 */

public class VideoRemote implements VideoDataSource {
    private VideoApi api;

    public VideoRemote() {
        api = ApiHelper.client().create(VideoApi.class);
    }

    @Override
    public void load(String movieId, final LoadVideosCallback callback) {
        Call<MovieVideoResponse> call = api.getAll(movieId);
        call.enqueue(new Callback<MovieVideoResponse>() {
            @Override
            public void onResponse(Call<MovieVideoResponse> call, Response<MovieVideoResponse> response) {
                if(response.isSuccessful()) {
                    MovieVideoResponse movieVideoResponse = response.body();
                    List<Video> videos = response.body().getVideos();
                    if(videos.size() > 0) {
                        callback.onLoaded(videos);
                    } else {
                        callback.onFailed("NO DATA");
                    }
                } else {
                    callback.onFailed(response.message());
                }
            }

            @Override
            public void onFailure(Call<MovieVideoResponse> call, Throwable t) {
                callback.onFailed(t.toString());
            }
        });
    }
}
