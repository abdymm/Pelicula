package com.abdymalikmulky.perfilman;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

import timber.log.Timber;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 7/7/17.
 */

public class PerfilmanApplication extends Application{

    private static PerfilmanApplication instance;

    public static PerfilmanApplication getInstance() { return instance; }

    @Override
    public void onCreate() {
        super.onCreate();

        FlowManager.init(this);
        Timber.plant(new Timber.DebugTree());

    }
}
