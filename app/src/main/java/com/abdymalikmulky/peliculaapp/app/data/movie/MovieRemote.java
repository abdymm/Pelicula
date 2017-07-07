package com.abdymalikmulky.peliculaapp.app.data.movie;

import com.abdymalikmulky.peliculaapp.app.data.movie.response.DiscoverMovieResponse;
import com.abdymalikmulky.peliculaapp.helper.ApiHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 7/7/17.
 */

public class MovieRemote implements MovieDataSource {

    MovieApi movieApi;

    public MovieRemote() {
        movieApi = ApiHelper.client().create(MovieApi.class);
    }

    @Override
    public void load(String filter, final LoadMoviesCallback callback) {
        Call<DiscoverMovieResponse> call = movieApi.getAll(filter);
        call.enqueue(new Callback<DiscoverMovieResponse>() {
            @Override
            public void onResponse(Call<DiscoverMovieResponse> call, Response<DiscoverMovieResponse> response) {
                if(response.isSuccessful()) {
                    DiscoverMovieResponse discoverMovieResponse = response.body();
                    List<Movie> movies = response.body().getMovies();
                    if(movies.size() > 0) {
                        callback.onLoaded(movies);
                    } else {
                        callback.onFailed("NO DATA");
                    }
                } else {
                    callback.onFailed(response.message());
                }
            }

            @Override
            public void onFailure(Call<DiscoverMovieResponse> call, Throwable t) {
                callback.onFailed(t.getMessage());
            }
        });
    }
}
