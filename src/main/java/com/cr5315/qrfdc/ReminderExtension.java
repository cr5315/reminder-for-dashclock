package com.cr5315.qrfdc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.android.apps.dashclock.api.DashClockExtension;
import com.google.android.apps.dashclock.api.ExtensionData;

/**
 * Created by Ben on 9/13/13.
 */
public class ReminderExtension extends DashClockExtension {
    SharedPreferences prefs;

    @Override
    protected void onUpdateData(int reason) {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        ExtensionData data = new ExtensionData();
        data.visible(isVisible());
        data.icon(getIcon());
        data.expandedBody(getBody());
        data.expandedTitle(getTitle());
        data.status(getStatus());
        data.clickIntent(getClickIntent());

        publishUpdate(data);
    }

    private String getTitle() {
        String s = prefs.getString(getString(R.string.pref_code_title), null);
        if (s != null) {
            return s;
        } else {
            return getString(R.string.error_no_title);
        }
    }

    private String getBody() {
        String s = prefs.getString(getString(R.string.pref_code_body), null);
        if (s != null) {
            return s;
        } else {
            return getString(R.string.error_no_body);
        }
    }

    private String getStatus() {
        if (!getTitle().matches(getString(R.string.error_no_title))) {
            return getTitle();
        } else {
            return getString(R.string.error_no_status);
        }
    }

    private Intent getClickIntent() {
        return new Intent(getApplicationContext(), ReminderSettings.class);
    }

    private int getIcon() {
        if (getTitle().matches(getString(R.string.error_no_title))) {
            return R.drawable.ic_error;
        } else {
            return R.drawable.ic_launcher;
        }
    }

    private boolean isVisible() {
        if (getTitle().matches("") && getBody().matches("")) return false;
        else return true;
    }
}
