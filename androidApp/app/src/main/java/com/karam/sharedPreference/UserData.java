package com.karam.sharedPreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

// TODO NEW_COMER Encrypt data being stored as shared preference
//  https://stackoverflow.com/questions/785973/what-is-the-most-appropriate-way-to-store-user-settings-in-android-application
// TODO NEW_COMER check if this should be singleton

public class UserData {
    private SharedPreferences prefs;
    private Context context;
    private String username = "usr";
    private String password = "pwd";
    private String user_state = "us";
    private String user_id = "uid";

    private int NOT_LOGGED_IN = 0;
    private int CONTRACTOR = 1;
    private int LABORER = 2;

    public UserData(Context cntx) {
        // TODO Auto-generated contractor stub
        this.context = cntx;
        // prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public int get_user_id() {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getInt(this.user_id, 0);
    }

    public void set_user_id(int user_id) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putInt(this.user_id, user_id).commit();
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
        prefs.edit().putString(username, usename).commit();
    }

    public String getPassword() {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String password = prefs.getString(this.password, null);
        return password;
    }

    public void setPassword(String password) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString(this.password, password).commit();
    }

    public void setUserStateLoggedOut() {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putInt(this.user_state, NOT_LOGGED_IN).commit();
    }

    public void setUserStateContractor() {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putInt(this.user_state, CONTRACTOR).commit();
    }

    public void setUserStateLaborer() {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putInt(this.user_state, LABORER).commit();
    }

    public int getUserState() {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getInt(this.user_state, 0);
    }
}