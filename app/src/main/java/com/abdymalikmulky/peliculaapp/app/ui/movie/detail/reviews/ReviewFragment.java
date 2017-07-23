package com.abdymalikmulky.peliculaapp.app.ui.movie.detail.reviews;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abdymalikmulky.peliculaapp.R;
import com.abdymalikmulky.peliculaapp.app.data.review.Review;
import com.abdymalikmulky.peliculaapp.app.data.video.Video;

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

    public ReviewFragment() {
        // Required empty public constructor
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

    private void initListReviewLayout() {
        reviews = new ArrayList<>();
        for (int i=0; i<10; i++) {
            reviews.add(new Review());
        }
        listReview.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        listReview.setLayoutManager(layoutManager);
        reviewListAdapter = new ReviewListAdapter(reviews, this);
        listReview.setAdapter(reviewListAdapter);
    }


    @Override
    public void setPresenter(ReviewContract.Presenter presenter) {

    }

    @Override
    public void showReviews(List<Review> reviews) {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void onClicked(Video video) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
