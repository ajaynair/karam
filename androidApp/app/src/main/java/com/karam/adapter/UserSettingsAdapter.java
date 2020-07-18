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
    private static final String[] settings = {"Language"};

    // TODO Store context of the caller activity if required
    public UserSettingsAdapter(Context context, int simple_list_item_1) {
        super(context, simple_list_item_1, settings);
    }

    /**
     * Called when settings is clicked.
     * TODO: Check all the other user settings and fill it up
     *
     * @param c: Context of the caller activity
     * @param i: Index of the item clicked
     * @param a: Caller activity
     */
    public void showSettings(Context c, int i, Activity a) {
    }
}
