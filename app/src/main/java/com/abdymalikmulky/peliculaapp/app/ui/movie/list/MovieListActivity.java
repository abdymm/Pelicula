package com.abdymalikmulky.peliculaapp.app.ui.movie.list;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.abdymalikmulky.peliculaapp.R;
import com.abdymalikmulky.peliculaapp.app.data.movie.Movie;
import com.abdymalikmulky.peliculaapp.app.data.movie.MovieLocal;
import com.abdymalikmulky.peliculaapp.app.data.movie.MovieRemote;
import com.abdymalikmulky.peliculaapp.app.data.movie.MovieRepo;
import com.abdymalikmulky.peliculaapp.app.ui.movie.detail.DetailActivity;
import com.abdymalikmulky.peliculaapp.app.ui.movie.settings.SettingsActivity;
import com.abdymalikmulky.peliculaapp.util.ConstantsUtil;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieListActivity extends AppCompatActivity implements MovieListContract.View {

    //SETTING SP
    SharedPreferences sp;


    //REPO
    private MovieRepo movieRepo;

    //PRESENTER
    private MovieListContract.Presenter movieListPresenter;

    //VIEW COMPONENT
    @BindView(R.id.list_movie)
    RecyclerView listMovie;
    @BindView(R.id.loading_movie)
    ProgressBar loadingMovie;
    @BindView(R.id.tv_error_global_msg)
    TextView tvErrorGlobalMsg;


    private List<Movie> movies;
    private MovieListAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupPreferenceSetting();

        movies = new ArrayList<>();

        movieRepo = new MovieRepo(getApplicationContext(), new MovieLocal(), new MovieRemote());
        movieListPresenter = new MovieListPresenter(this, movieRepo);

        initListMovieLayout();
    }

    @Override
    protected void onResume() {
        super.onResume();
        showHideLoadingList(loadingMovie, listMovie, true);
        movieListPresenter.loadMovies(getSortBy());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_setting:
                Intent settingIntent = new Intent(this, SettingsActivity.class);
                startActivity(settingIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setPresenter(MovieListContract.Presenter presenter) {
        movieListPresenter = presenter;
    }

    @Override
    public void showMovies(List<Movie> movies) {
        showHideLoadingList(loadingMovie, listMovie, false);
        listMovie.setVisibility(View.VISIBLE);

        movieAdapter.refresh(movies);
    }

    @Override
    public void showError(String msg) {
        showHideLoadingList(loadingMovie, listMovie, false);
        tvErrorGlobalMsg.setVisibility(View.VISIBLE);
        tvErrorGlobalMsg.setText(msg);
    }

    @Override
    public void onListClicked(Movie movie) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        detailIntent.putExtra(ConstantsUtil.INTENT_MOVIE, Parcels.wrap(movie));
        startActivity(detailIntent);
    }

    private String getSortBy() {
        return sp.getString(ConstantsUtil.SP_SORTBY, ConstantsUtil.MOVIE_LIST_SORT_BY_POPULARITY_DESC);
    }

    private void setupPreferenceSetting() {
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        sp.edit().putString(ConstantsUtil.SP_SORTBY, ConstantsUtil.MOVIE_LIST_SORT_BY_POPULARITY_DESC).apply();
    }

    private void initListMovieLayout() {

        listMovie.setHasFixedSize(true);

        int columns;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            columns = 3;
        } else {
            columns = 2;
        }
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, columns);

        listMovie.setLayoutManager(layoutManager);
        movieAdapter = new MovieListAdapter(movies, this);
        listMovie.setAdapter(movieAdapter);
    }

    private void showHideLoadingList(ProgressBar pb, RecyclerView rv, boolean show) {
        if (show) {
            pb.setVisibility(View.VISIBLE);
            rv.setVisibility(View.GONE);
        } else {
            pb.setVisibility(View.GONE);
            rv.setVisibility(View.VISIBLE);
        }
    }
}
