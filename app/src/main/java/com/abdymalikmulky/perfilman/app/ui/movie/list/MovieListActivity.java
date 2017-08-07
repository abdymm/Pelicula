package com.abdymalikmulky.perfilman.app.ui.movie.list;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
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
import android.widget.Toast;

import com.abdymalikmulky.perfilman.R;
import com.abdymalikmulky.perfilman.app.data.movie.Movie;
import com.abdymalikmulky.perfilman.app.data.movie.MovieLocal;
import com.abdymalikmulky.perfilman.app.data.movie.MovieRemote;
import com.abdymalikmulky.perfilman.app.data.movie.MovieRepo;
import com.abdymalikmulky.perfilman.app.ui.movie.detail.DetailActivity;
import com.abdymalikmulky.perfilman.app.ui.movie.settings.SettingsActivity;
import com.abdymalikmulky.perfilman.util.ConstantsUtil;
import com.dinuscxj.refresh.RecyclerRefreshLayout;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MovieListActivity extends AppCompatActivity implements MovieListContract.View, RecyclerRefreshLayout.OnRefreshListener {

    //state
    public final static String LIST_STATE_KEY = "recycler_list_state";
    Parcelable listState;

    //SETTING SP
    SharedPreferences settingSp;


    //REPO
    private MovieRepo movieRepo;

    //PRESENTER
    private MovieListContract.Presenter movieListPresenter;

    //VIEW COMPONENT
    @BindView(R.id.swipe_refresh_movie)
    RecyclerRefreshLayout swipeRefreshMovie;
    @BindView(R.id.list_movie)
    RecyclerView listMovie;
    @BindView(R.id.loading_movie)
    ProgressBar loadingMovie;
    @BindView(R.id.tv_error_global_msg)
    TextView tvErrorGlobalMsg;
    @BindView(R.id.loadmore_movie)
    ProgressBar loadmoreMovie;


    private List<Movie> movies;
    private MovieListAdapter movieAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private int pageCount = 1;
    private boolean loadMoreState = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        setupPreferenceSetting();

        movies = new ArrayList<>();

        movieRepo = new MovieRepo(getApplicationContext(), new MovieLocal(getApplicationContext()), new MovieRemote());
        movieListPresenter = new MovieListPresenter(this, movieRepo);

        initListMovieLayout();

        showHideLoadingOrError(true, false);
        movieListPresenter.loadMovies(getSortBy());

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (listState != null) {
            layoutManager.onRestoreInstanceState(listState);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        listState = layoutManager.onSaveInstanceState();
        outState.putParcelable(LIST_STATE_KEY, listState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null)
            listState = savedInstanceState.getParcelable(LIST_STATE_KEY);
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
        pageCount++;
        showHideLoadingOrError(false, false);
        listMovie.setVisibility(View.VISIBLE);
        movieAdapter.refresh(movies);
    }

    @Override
    public void showLoadMoreMovies(List<Movie> movies) {
        finishLoadMore(true);
        movieAdapter.add(movies);
    }

    @Override
    public void showError(String msg) {
        tvErrorGlobalMsg.setText(msg);
        showHideLoadingOrError(false, true);
    }

    @Override
    public void showErrorLoadMore(String msg) {
        finishLoadMore(false);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onListClicked(Movie movie) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        detailIntent.putExtra(ConstantsUtil.INTENT_MOVIE, Parcels.wrap(movie));
        startActivity(detailIntent);
    }

    private String getSortBy() {
        return settingSp.getString(ConstantsUtil.SP_SORTBY, ConstantsUtil.MOVIE_LIST_SORT_BY_POPULARITY_DESC);
    }

    private void setupPreferenceSetting() {
        settingSp = PreferenceManager.getDefaultSharedPreferences(this);
    }

    private void initListMovieLayout() {
        listMovie.setHasFixedSize(true);

        int columns;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            columns = 3;
        } else {
            columns = 2;
        }
        layoutManager = new GridLayoutManager(this, columns);

        listMovie.setLayoutManager(layoutManager);
        movieAdapter = new MovieListAdapter(movies, this);
        listMovie.setAdapter(movieAdapter);


        listMovie.setOnScrollListener(new MovieListScrolllListener(pageCount) {
            @Override
            public void onLoadMore(int current_page) {
                Timber.d("Current_page %s", current_page);
                if (loadMoreState) {
                    loadMoreState = false;
                    showLoadingMore(true);
                    movieListPresenter.loadMoreMovies(pageCount, getSortBy());
                }
            }
        });

        //TODO:pndahinn ke presenter
        swipeRefreshMovie.setOnRefreshListener(this);
    }

    private void finishLoadMore(boolean success){
        if(success){
            pageCount++;
        }
        loadMoreState = true;
        showLoadingMore(false);
    }
    private void showLoadingMore(boolean show){
        if(!show){
            loadmoreMovie.setVisibility(View.GONE);
        }else {
            loadmoreMovie.setVisibility(View.VISIBLE);
        }
    }
    private void showHideLoadingOrError(boolean showLoading, boolean showError) {
        ConstantsUtil.showHideLoadingList(loadingMovie, listMovie, tvErrorGlobalMsg, showLoading, showError);
    }


    @Override
    public void onRefresh() {
        pageCount =1;
        showHideLoadingOrError(false, false);
        swipeRefreshMovie.setRefreshing(false);
        movieListPresenter.loadMovies(getSortBy());
    }
}
