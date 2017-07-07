package com.abdymalikmulky.peliculaapp.app.ui.movie.list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.abdymalikmulky.peliculaapp.R;
import com.abdymalikmulky.peliculaapp.app.data.movie.Movie;
import com.abdymalikmulky.peliculaapp.app.data.movie.MovieLocal;
import com.abdymalikmulky.peliculaapp.app.data.movie.MovieRemote;
import com.abdymalikmulky.peliculaapp.app.data.movie.MovieRepo;
import com.abdymalikmulky.peliculaapp.util.ConstantsUtil;

import java.util.List;

import timber.log.Timber;

public class MovieListActivity extends AppCompatActivity implements MovieListContract.View {

    //REPO
    MovieRepo movieRepo;

    //PRESENTER
    MovieListContract.Presenter movieListPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieRepo = new MovieRepo(new MovieLocal(), new MovieRemote());

        movieListPresenter = new MovieListPresenter(this, movieRepo);
    }

    @Override
    protected void onResume() {
        super.onResume();
        movieListPresenter.loadMovies(ConstantsUtil.MOVIE_LIST_SORT_BY_POPULARITY_DESC);
    }

    @Override
    public void setPresenter(MovieListContract.Presenter presenter) {
        movieListPresenter = presenter;
    }

    @Override
    public void showMovies(List<Movie> movies) {
        for (Movie movie : movies) {
            Timber.d("Movies : %s"+movie.toString());
        }
    }

    @Override
    public void showError(String msg) {
        Timber.e("Movies-Error %s", msg);
    }
}
