package com.abdymalikmulky.perfilman.app.ui.movie.list;

import android.support.v7.widget.RecyclerView;

import timber.log.Timber;

/**
 * Created by abdymalikmulky on 2/5/17.
 */

public abstract class MovieListScrolllListener
        extends RecyclerView.OnScrollListener {
    int currentPage;

    public MovieListScrolllListener(int currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public final void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        Timber.d("DX : %s | DY : %s",dx,dy);
        if (!recyclerView.canScrollVertically(-1)) {
            onScrolledToTop();
        } else if (!recyclerView.canScrollVertically(1)) {
            onScrolledToBottom(currentPage);
        } else if (dy < 0) {
            onScrolledUp();
        } else if (dy > 0) {
            onScrolledDown();
        }
    }

    public void onScrolledUp() {
        Timber.d("onScrolledUp");
    }

    public void onScrolledDown() {
        Timber.d("onScrolledDown");
    }

    public void onScrolledToTop() {
        Timber.d("onScrolledToTop");

    }

    public void onScrolledToBottom(int currentPage) {
        onLoadMore(currentPage);
    }

    public abstract void onLoadMore(int current_page);

}