package com.abdymalikmulky.perfilman.app.data.movie;

import com.abdymalikmulky.perfilman.app.data.movie.response.DiscoverMovieResponse;
import com.abdymalikmulky.perfilman.helper.ApiHelper;

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
        //TODO: Second parameter for paging, for stage 2, now i just use first page
        Call<DiscoverMovieResponse> call = movieApi.getAll(filter, 1);
        call.enqueue(new Callback<DiscoverMovieResponse>() {
            @Override
            public void onResponse(Call<DiscoverMovieResponse> call, Response<DiscoverMovieResponse> response) {
                if(response.isSuccessful()) {
                    DiscoverMovieResponse discoverMovieResponse = response.body();
                    List<Movie> movies = discoverMovieResponse.getMovies();
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
                callback.onFailed(t.toString());
            }
        });
    }
}
