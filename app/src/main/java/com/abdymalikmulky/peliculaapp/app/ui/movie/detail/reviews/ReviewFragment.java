package com.abdymalikmulky.peliculaapp.app.ui.movie.detail.reviews;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.abdymalikmulky.peliculaapp.R;
import com.abdymalikmulky.peliculaapp.app.data.review.Review;
import com.abdymalikmulky.peliculaapp.app.data.review.ReviewLocal;
import com.abdymalikmulky.peliculaapp.app.data.review.ReviewRemote;
import com.abdymalikmulky.peliculaapp.app.data.review.ReviewRepo;
import com.abdymalikmulky.peliculaapp.util.ConstantsUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewFragment extends Fragment implements ReviewContract.View {


    @BindView(R.id.movie_detail_subtitle_review)
    TextView movieDetailSubtitleReview;
    @BindView(R.id.detail_list_review)
    RecyclerView listReview;

    Unbinder unbinder;

    private List<Review> reviews;
    private ReviewListAdapter reviewListAdapter;

    private ReviewLocal reviewLocal;
    private ReviewRemote reviewRemote;
    private ReviewRepo reviewRepo;

    private ReviewContract.Presenter reviewPresenter;

    private String movieId;


    public ReviewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reviewLocal = new ReviewLocal();
        reviewRemote = new ReviewRemote();
        reviewRepo = new ReviewRepo(reviewLocal, reviewRemote);

        reviewPresenter = new ReviewPresenter(reviewRepo, this);
        movieId = getArguments().getString(ConstantsUtil.INTENT_MOVIE_ID, "");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_review, container, false);
        unbinder = ButterKnife.bind(this, view);

        initListReviewLayout();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        reviewPresenter.loadReviews(movieId);
    }

    private void initListReviewLayout() {
        reviews = new ArrayList<>();
        listReview.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        listReview.setLayoutManager(layoutManager);
        reviewListAdapter = new ReviewListAdapter(reviews, this);
        listReview.setAdapter(reviewListAdapter);
    }


    @Override
    public void setPresenter(ReviewContract.Presenter presenter) {
        reviewPresenter = presenter;
    }

    @Override
    public void showReviews(List<Review> reviews) {
        this.reviews = reviews;
        reviewListAdapter.refresh(reviews);
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClicked(Review review) {
        Intent detailReviewIntent = new Intent(Intent.ACTION_VIEW);
        detailReviewIntent.setData(Uri.parse(review.getUrl()));
        startActivity(detailReviewIntent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
