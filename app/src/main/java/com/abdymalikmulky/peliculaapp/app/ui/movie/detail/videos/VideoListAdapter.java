package com.abdymalikmulky.peliculaapp.app.ui.movie.detail.videos;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.abdymalikmulky.peliculaapp.R;
import com.abdymalikmulky.peliculaapp.app.data.video.Video;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 7/7/17.
 */

public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.ViewHolder> {


    private List<Video> videos;
    private Context context;
    private VideoContract.View trailerView;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.movie_detail_video_thumbnail)
        ImageView movieDetailVideoThumbnail;

        public Video video;

        public ViewHolder(View root) {
            super(root);
            ButterKnife.bind(this, root);
        }

        @Override
        public void onClick(View view) {
            trailerView.onClicked(video);
        }
    }

    public VideoListAdapter(List<Video> videos, VideoContract.View trailerView) {
        this.videos = videos;
        this.trailerView = trailerView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View rootView = LayoutInflater.from(context).inflate(R.layout.grid_item_trailer, parent, false);

        return new ViewHolder(rootView);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(holder);

        holder.video = videos.get(position);

        Picasso.with(context)
                .load(holder.video.getThumbnailUrl(holder.video))
                .placeholder(R.drawable.blank_movie_poster)
                .error(R.drawable.blank_movie_poster)
                .into(holder.movieDetailVideoThumbnail);

    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public void refresh(List<Video> videos) {
        this.videos.clear();
        this.videos = videos;
        this.videos.remove(0);
        notifyDataSetChanged();
    }
}
