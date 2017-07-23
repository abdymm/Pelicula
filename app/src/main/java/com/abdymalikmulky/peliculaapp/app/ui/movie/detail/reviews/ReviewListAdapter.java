package com.abdymalikmulky.peliculaapp.app.ui.movie.detail.reviews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abdymalikmulky.peliculaapp.R;
import com.abdymalikmulky.peliculaapp.app.data.review.Review;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 7/7/17.
 */

public class ReviewListAdapter extends RecyclerView.Adapter<ReviewListAdapter.ViewHolder> {



    private List<Review> reviews;
    private Context context;
    private ReviewContract.View reviewView;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.review_user_name)
        TextView reviewUserName;
        @BindView(R.id.review_content)
        TextView reviewContent;

        public Review review;

        public ViewHolder(View root) {
            super(root);
            ButterKnife.bind(this, root);
        }

        @Override
        public void onClick(View view) {
            reviewView.onClicked(review);
        }
    }

    public ReviewListAdapter(List<Review> reviews, ReviewContract.View reviewView) {
        this.reviews = reviews;
        this.reviewView = reviewView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View rootView = LayoutInflater.from(context).inflate(R.layout.list_item_review, parent, false);

        return new ViewHolder(rootView);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(holder);

        holder.review = reviews.get(position);

        holder.reviewUserName.setText(holder.review.getAuthor());
        holder.reviewContent.setText(holder.review.getContent());

    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public void refresh(List<Review> reviews) {
        this.reviews.clear();
        this.reviews = reviews;
        notifyDataSetChanged();
    }
}
