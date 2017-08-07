package com.abdymalikmulky.perfilman.app.ui.movie.detail;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.abdymalikmulky.perfilman.R;
import com.abdymalikmulky.perfilman.app.data.favorite.Favorite;
import com.abdymalikmulky.perfilman.app.data.favorite.FavoriteLocal;
import com.abdymalikmulky.perfilman.app.data.favorite.FavoriteRemote;
import com.abdymalikmulky.perfilman.app.data.favorite.FavoriteRepo;
import com.abdymalikmulky.perfilman.app.data.movie.Movie;
import com.abdymalikmulky.perfilman.app.data.video.Video;
import com.abdymalikmulky.perfilman.app.data.video.VideoLocal;
import com.abdymalikmulky.perfilman.app.data.video.VideoRemote;
import com.abdymalikmulky.perfilman.app.data.video.VideoRepo;
import com.abdymalikmulky.perfilman.app.ui.movie.detail.reviews.ReviewFragment;
import com.abdymalikmulky.perfilman.app.ui.movie.detail.videos.VideoFragment;
import com.abdymalikmulky.perfilman.util.ConstantsUtil;
import com.abdymalikmulky.perfilman.util.DateTimeUtil;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

import static com.raizlabs.android.dbflow.config.FlowManager.getContext;

public class DetailActivity extends AppCompatActivity implements DetailContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.movie_detail_backdrop)
    ImageView movieDetailBackdrop;
    @BindView(R.id.movie_detail_title)
    TextView movieDetailTitle;
    @BindView(R.id.movie_detail_years)
    TextView movieDetailYears;
    @BindView(R.id.movie_detail_poster)
    ImageView movieDetailPoster;
    @BindView(R.id.movie_detail_overview)
    TextView movieDetailOverview;
    @BindView(R.id.movie_detail_rating)
    TextView movieDetailRating;
    @BindView(R.id.movie_detail_rating_count)
    TextView movieDetailRatingCount;
    @BindView(R.id.fragment_trailer)
    FrameLayout fragmentTrailer;
    @BindView(R.id.movie_detail_youtube_ic)
    ImageView movieDetailYoutubeIc;
    @BindView(R.id.movie_detail_youtube_label)
    TextView movieDetailYoutubeLabel;
    @BindView(R.id.movie_detail_favorite)
    FloatingActionButton movieDetailFavorite;
    @BindView(R.id.movie_trailer_share)
    RelativeLayout movieTrailerShare;

    private boolean isFavorited = false;

    private DetailContract.Presenter detailPresenter;
    private Movie movie;
    private Favorite favoritedMovie;

    private FavoriteLocal favoriteLocal;
    private FavoriteRemote favoriteRemote;
    private FavoriteRepo favoriteRepo;

    private VideoLocal videoLocal;
    private VideoRemote videoRemote;
    private VideoRepo videoRepo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        setToolbar();


        try {
            movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(ConstantsUtil.INTENT_MOVIE));
        } catch (NullPointerException e) {
            Timber.e(e.toString());
        }
        favoritedMovie = new Favorite();

        initRepo();
        detailPresenter = new DetailPresenter(favoriteRepo, videoRepo, this);

        initFragment();
    }

    private void initFragment() {
        setupTrailerFragment();
        setupReviewFragment();
    }

    private void initRepo() {
        videoLocal = new VideoLocal();
        videoRemote = new VideoRemote();
        videoRepo = new VideoRepo(videoLocal, videoRemote);

        favoriteLocal = new FavoriteLocal(getApplicationContext());
        favoriteRemote = new FavoriteRemote();
        favoriteRepo = new FavoriteRepo(favoriteLocal, favoriteRemote);
    }



    @Override
    protected void onResume() {
        super.onResume();
        detailPresenter.start();
        detailPresenter.isFavorited(movie.getId());
        detailPresenter.loadFirstVideo(movie.getId());
    }

    @OnClick(R.id.movie_detail_favorite)
    public void favorite(View view) {
        if(!isFavorited) {
            detailPresenter.updateToFavorite(movie);
        } else {
            detailPresenter.updateToUnfavorite(favoritedMovie.getId());
        }
    }

    private void setToolbar() {
        collapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        collapsingToolbarLayout.setTitle(getString(R.string.title_detail_movie));
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedToolbar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedToolbar);
        collapsingToolbarLayout.setTitleEnabled(true);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        } else {
            //
        }
    }

    //MOVIE
    @Override
    public void setPresenter(DetailContract.Presenter presenter) {
        detailPresenter = presenter;
    }

    @Override
    public void showMovie() {
        movieDetailTitle.setText(movie.getTitle());
        movieDetailOverview.setText(movie.getOverview());
        movieDetailYears.setText(DateTimeUtil.convertToHumanReadableDate(movie.getReleaseDate()));
        movieDetailRating.setText(String.valueOf(movie.getVoteAverage()));
        movieDetailRatingCount.setText(String.valueOf(movie.getVoteCount()));

        Picasso.with(getApplicationContext())
                .load(movie.getFullPosterPath())
                .placeholder(R.drawable.blank_movie_poster)
                .error(R.drawable.blank_movie_poster)
                .into(movieDetailPoster);

        Picasso.with(getApplicationContext())
                .load(movie.getFullBackdropPath())
                .placeholder(R.drawable.blank_movie_poster)
                .error(R.drawable.blank_movie_poster)
                .into(movieDetailBackdrop);
    }

    @Override
    public void showError(String msg) {
        //Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFirstVideoInToolbar(final Video video) {
        movieDetailYoutubeIc.setVisibility(View.VISIBLE);
        movieDetailYoutubeLabel.setVisibility(View.VISIBLE);

        Picasso.with(getApplicationContext())
                .load(video.getThumbnailUrl(video))
                .placeholder(R.drawable.blank_movie_poster)
                .error(R.drawable.blank_movie_poster)
                .into(movieDetailBackdrop);

        movieDetailBackdrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConstantsUtil.openVideoIntent(DetailActivity.this, video);
            }
        });

        movieDetailBackdrop.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String sharingText = "This is first trailer of " + movie.getTitle() + " " + video.getUrl(video);
                ConstantsUtil.shareVideoIntent(DetailActivity.this, sharingText, video);
                return true;
            }
        });
    }

    @Override
    public void showFavorited(Favorite favoriteMovie) {
        isFavorited = true;
        this.favoritedMovie = favoriteMovie;
        movieDetailFavorite.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_favorite_24dp));
    }

    @Override
    public void showUnfavorited() {
        isFavorited = false;
        movieDetailFavorite.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_unfavorite_24dp));
    }


    private void setupTrailerFragment() {
        addFragmentMovieToActivity(R.id.fragment_trailer, new VideoFragment(), movie.getId());
    }

    private void setupReviewFragment() {
        addFragmentMovieToActivity(R.id.fragment_reviews, new ReviewFragment(), movie.getId());
    }

    private void addFragmentMovieToActivity(int fragmentLayout, Fragment fragmentObj, String movieId) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ConstantsUtil.INTENT_MOVIE_ID, movieId);
        fragmentObj.setArguments(bundle);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(fragmentLayout, fragmentObj);
        ft.commit();
    }
}
