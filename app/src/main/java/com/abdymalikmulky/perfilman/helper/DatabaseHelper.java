package com.abdymalikmulky.perfilman.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.abdymalikmulky.perfilman.app.data.movie.MovieLocal;

/**
 * Bismillahirrahmanirrahim
 * Created by Innovation, eFishery  on 5/24/17.
 */

public abstract class DatabaseHelper extends SQLiteOpenHelper {

    // Database fields
    private SQLiteDatabase database;

    private static final String DATABASE_NAME = "perfilman.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(MovieLocal.CREATE_TABLE_MOVIE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MovieLocal.CREATE_TABLE_MOVIE);
        onCreate(db);
    }

    public void open() throws SQLException {
        database = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }

    public boolean checkQueryDbSuccess(long executionResult){
        return executionResult > 0;
    }

    public long insertAll(String table, ContentValues values) {
        long newSettingId = getDatabase().insert(table, null, values);
        return newSettingId;
    }

    public abstract  <T> T cursorToItem(Cursor cursor);

}
