package com.karam.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.karam.sharedPreference.UserData;
import com.karam.utils.AppLocale;
import com.karam.view.activity.R;

/**
 * Adapter for languages that users can select for the app
 *
 * @param <T>: String which represents the languages
 */
public class LanguageArrayAdapter<T> extends ArrayAdapter<String> {
    /**
     * TODO: Find a better data structure for languages
     */
    private static final String[] languages = {
            "English",
            "Hindi",
            "Marathi"};
    private final Context context;

    public LanguageArrayAdapter(Context context, int simple_list_item_1) {
        super(context, simple_list_item_1, languages);
        this.context = context;
    }

    /**
     * Get the language abbreviation as required by android based on the listview element selected by the user
     *
     * @param i: id of the element in the listview that is selected by the user click
     * @return abbreviation of the language as required by android library to set the default language
     */
    private String getLanguageAbbr(int i) {
        switch (languages[i]) {
            case "English":
                return "en";
            case "Hindi":
                return "hi";
            case "Marathi":
                return "mr";
            default:
                return "en";
        }
    }

    /**
     * Sets the language configuration of the application
     *
     * @param c: context of the current activity
     * @param i: index of the listview element that is selected
     * @param a: activity to open after refresh
     */
    public void setLocale(Context c, int i, Activity a, UserData u) {
        String lang = getLanguageAbbr(i);

        AppLocale.set_language(c, a, lang);
        Intent refresh = new Intent(a, a.getClass());
        a.finish();
        a.startActivity(refresh);

        u.set_current_language(lang);
    }

    /**
     * Returns the view for the language listview
     */
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = super.getView(position, view, parent);
        TextView t;
        switch (position) {
            case 1:
                t = (TextView) view;
                t.setText(R.string.common_hindi);
                break;
            case 2:
                t = (TextView) view;
                t.setText(R.string.common_marathi);
                break;
            default:
                t = (TextView) view;
                t.setText(R.string.common_english);
                break;
        }
        return view;
    }
}
