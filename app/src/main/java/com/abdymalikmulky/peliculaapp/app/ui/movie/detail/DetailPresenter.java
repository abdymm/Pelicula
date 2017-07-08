package com.abdymalikmulky.peliculaapp.app.ui.movie.detail;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 7/8/17.
 */

public class DetailPresenter implements DetailContract.Presenter {

    DetailContract.View detailView;

    public DetailPresenter(DetailContract.View detailView) {
        this.detailView = detailView;
        this.detailView.setPresenter(this);
    }

    @Override
    public void start() {
        detailView.showMovie();
    }

    @Override
    public void stop() {

    }
}
