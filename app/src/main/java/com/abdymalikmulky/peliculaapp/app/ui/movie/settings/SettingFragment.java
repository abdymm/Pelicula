package com.abdymalikmulky.peliculaapp.app.ui.movie.settings;


import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.abdymalikmulky.peliculaapp.R;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 7/9/17.
 */

public class SettingFragment extends PreferenceFragmentCompat{

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.pref_main);
    }
}
