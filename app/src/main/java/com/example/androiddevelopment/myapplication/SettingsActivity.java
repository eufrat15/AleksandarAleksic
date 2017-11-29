package com.example.androiddevelopment.myapplication;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by androiddevelopment on 29.11.17..
 */

public class SettingsActivity extends PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
    }
}
