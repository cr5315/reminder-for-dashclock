package com.cr5315.qrfdc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.view.MenuItem;

/**
 * Created by Ben on 9/13/13.
 */
public class ReminderSettings extends PreferenceActivity {
    Preference body, title;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();

        addPreferencesFromResource(R.xml.prefs);

        body = (Preference) findPreference(getResources().getString(R.string.pref_code_body));
        body.setSummary(prefs.getString(getString(R.string.pref_code_body), null));
        body.setOnPreferenceChangeListener(new BodyChangeListener());
        title = (Preference) findPreference(getResources().getString(R.string.pref_code_title));
        title.setSummary(prefs.getString(getString(R.string.pref_code_title), null));
        title.setOnPreferenceChangeListener(new TitleChangeListener());
    }

    private class BodyChangeListener implements Preference.OnPreferenceChangeListener {
        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            preference.setSummary(newValue.toString());
            return true;
        }
    }

    private class TitleChangeListener implements Preference.OnPreferenceChangeListener {
        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            preference.setSummary(newValue.toString());
            return true;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }
}
