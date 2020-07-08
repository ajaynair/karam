package com.karam.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

// TODO NEW_COMER Encrypt data being stored as shared preference
//  https://stackoverflow.com/questions/785973/what-is-the-most-appropriate-way-to-store-user-settings-in-android-application
// TODO NEW_COMER check if this should be singleton

public class UserData {
    private SharedPreferences prefs;
    private String username = "usr";
    private String password = "pwd";
    private String user_state = "us";

    private int NOT_LOGGED_IN = 0;
    private int CONTRACTOR = 1;
    private int LABORER = 2;

    public int get_user_id() {
        return user_id;
    }

    public void set_user_id(int user_id) {
        this.user_id = user_id;
    }

    private int user_id = 0;

    public UserData(Context cntx) {
        // TODO Auto-generated contractor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
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
        String usename = prefs.getString(username, null);
        return usename;
    }

    public void setUsename(String usename) {
        prefs.edit().putString(username, usename).commit();
    }

    public String getPassword() {
        String password = prefs.getString(this.password, null);
        return password;
    }

    public void setPassword(String password) {
        prefs.edit().putString(this.password, password).commit();
    }

    public void setUserStateLoggedOut() {
        prefs.edit().putInt(this.user_state, NOT_LOGGED_IN).commit();
    }

    public void setUserStateContractor() {
        prefs.edit().putInt(this.user_state, CONTRACTOR).commit();
    }

    public void setUserStateLaborer() {
        prefs.edit().putInt(this.user_state, LABORER).commit();
    }

    public int getUserState() {
        int us = prefs.getInt(this.user_state, 0);
        return us;
    }
}