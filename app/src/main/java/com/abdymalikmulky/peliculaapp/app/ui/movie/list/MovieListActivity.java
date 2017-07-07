package com.abdymalikmulky.peliculaapp.app.ui.movie.list;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.abdymalikmulky.peliculaapp.R;
import com.abdymalikmulky.peliculaapp.app.data.movie.Movie;
import com.abdymalikmulky.peliculaapp.app.data.movie.MovieLocal;
import com.abdymalikmulky.peliculaapp.app.data.movie.MovieRemote;
import com.abdymalikmulky.peliculaapp.app.data.movie.MovieRepo;
import com.abdymalikmulky.peliculaapp.util.ConstantsUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MovieListActivity extends AppCompatActivity implements MovieListContract.View {

    //REPO
    private MovieRepo movieRepo;

    //PRESENTER
    private MovieListContract.Presenter movieListPresenter;

    //VIEW COMPONENT
    @BindView(R.id.list_movie)
    RecyclerView listMovie;

    private List<Movie> movies;
    private MovieListAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        movies = new ArrayList<>();

        movieRepo = new MovieRepo(new MovieLocal(), new MovieRemote());
        movieListPresenter = new MovieListPresenter(this, movieRepo);

        initListMovieLayout();
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
        listMovie.setVisibility(View.VISIBLE);
        movieAdapter.refresh(movies);
    }

    @Override
    public void showError(String msg) {
        Timber.e("Movies-Error %s", msg);
    }


    private void initListMovieLayout() {
        listMovie.setHasFixedSize(true);

        int columns;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            columns = 2;
        } else
        {
            columns = 2;
        }
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, columns);

        listMovie.setLayoutManager(layoutManager);
        movieAdapter = new MovieListAdapter(movies, this);
        listMovie.setAdapter(movieAdapter);
    }
}
