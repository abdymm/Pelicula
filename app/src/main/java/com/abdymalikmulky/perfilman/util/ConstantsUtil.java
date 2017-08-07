/*
 * Copyright 2017 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.abdymalikmulky.perfilman.util;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.abdymalikmulky.perfilman.app.data.video.Video;

/**
 * Bismillahirrahmanirrahim
 * abdymalikmulky
 * ------
 * Constant values reused in this sample.
 */
public class ConstantsUtil {

    //INTENT KEY
    public static final String INTENT_MOVIE = "movie";
    public static final String INTENT_MOVIE_ID = "movie_id";

    //SP KEY
    public static final String SP_SORTBY = "pref_sortby";

    public static final String MOVIE_LIST_SORT_BY_VOTE_AVERAGE = "top_rated";
    public static final String MOVIE_LIST_SORT_BY_POPULARITY_DESC = "popular";
    public static final String MOVIE_LIST_SORT_BY_MY_FAVORITES = "my_favorites";


    public static void openVideoIntent(Activity activity, Video video) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + video.getKey()));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(video.getUrl(video)));
        try {
            activity.startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            activity.startActivity(webIntent);
        }
    }

    public static void shareVideoIntent(Activity activity, String text, Video video) {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("video/mp4");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, text);
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, text);
        activity.startActivity(Intent.createChooser(sharingIntent,"Share on"));
    }

    /**
     LAYOUT VIEW
     **/
    public static void showHideLoadingList(ProgressBar pbLoading, RecyclerView rvList, TextView tvMsg,
                                           boolean showLoading, boolean showError) {
        if(showError) {
            pbLoading.setVisibility(View.GONE);
            rvList.setVisibility(View.GONE);
            tvMsg.setVisibility(View.VISIBLE);
        } else {
            tvMsg.setVisibility(View.GONE);
            if (showLoading) {
                pbLoading.setVisibility(View.VISIBLE);
                rvList.setVisibility(View.GONE);
            } else {
                pbLoading.setVisibility(View.GONE);
                rvList.setVisibility(View.VISIBLE);
            }
        }
    }
    public static void showErrorInLoadingList(TextView tvMsg, boolean show) {

    }
}
