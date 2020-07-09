package com.karam.adapter;

import android.app.Activity;
import android.content.Context;
import android.widget.ArrayAdapter;

/**
 * Adapter for languages that users can select for the app
 *
 * @param <T>: String which represents the languages
 */
public class UserSettingsAdapter<T> extends ArrayAdapter<String> {
    /**
     * TODO: Find a better data structure for languages
     */
    private static String[] settings = {"Language"};
    Context context;

    public UserSettingsAdapter(Context context, int simple_list_item_1) {
        super(context, simple_list_item_1, settings);
        this.context = context;
    }


    public void showSettings(Context c, int i, Activity a) {
    }
}
