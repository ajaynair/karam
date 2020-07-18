package com.karam.sharedPreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

// TODO NEW_COMER Encrypt data being stored as shared preference
//  https://stackoverflow.com/questions/785973/what-is-the-most-appropriate-way-to-store-user-settings-in-android-application
// TODO NEW_COMER check if this should be singleton

public class UserData {
    private SharedPreferences prefs;
    private final Context context;
    private final String username = "usr";
    private final String password = "pwd";
    private final String user_state = "us";
    private final String user_id = "uid";
    private final String language = "lang";

    private final int NOT_LOGGED_IN = 0;
    private final int CONTRACTOR = 1;
    private final int LABORER = 2;

    public UserData(Context cntx) {
        // TODO Test with .apply() instead of .apply()
        this.context = cntx;
        // prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public int get_user_id() {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getInt(this.user_id, 0);
    }

    public void set_user_id(int user_id) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putInt(this.user_id, user_id).apply();
    }


    public String get_current_language() {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(this.language, "en");
    }

    public void set_current_language(String l) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString(this.language, l).apply();
    }

    public int getNotLoggedInValue() {
        return NOT_LOGGED_IN;
    }

    public int getContractorValue() {
        return CONTRACTOR;
    }

    public int getLaborerValue() {
        return LABORER;
    }

    public String getUsename() {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(username, null);

    }

    public void setUsename(String usename) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString(username, usename).apply();
    }

    public String getPassword() {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(this.password, null);
    }

    public void setPassword(String password) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString(this.password, password).apply();
    }

    public void setUserStateLoggedOut() {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putInt(this.user_state, NOT_LOGGED_IN).apply();
    }

    public void setUserStateContractor() {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putInt(this.user_state, CONTRACTOR).apply();
    }

    public void setUserStateLaborer() {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putInt(this.user_state, LABORER).apply();
    }

    public int getUserState() {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getInt(this.user_state, 0);
    }
}