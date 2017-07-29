/*
* Copyright (C) 2016 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.abdymalikmulky.perfilman.app.data.favorite;

import android.net.Uri;
import android.provider.BaseColumns;


public class FavoriteContract {

    public static final String AUTHORITY = "com.abdymalikmulky.perfilman";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static final String PATH_FAVORITE_MOVIES = "favorite_movies";

    public static final class FavoriteEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_FAVORITE_MOVIES).build();

        public static final String TABLE_NAME = "favorite";

        public static final String COLUMN_MOVIE_ID = "movie_id";
        public static final String COLUMN_MOVIE_TITLE = "movie_title";

        public static final String CREATE_TABLE = "CREATE TABLE "  + TABLE_NAME + " (" +
                _ID                + " INTEGER PRIMARY KEY, " +
                COLUMN_MOVIE_ID + " TEXT NOT NULL, " +
                COLUMN_MOVIE_TITLE    + " TEXT NOT NULL);";

    }
}
