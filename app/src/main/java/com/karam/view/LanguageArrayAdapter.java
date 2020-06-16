package com.karam.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.karam.view.activity.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LanguageArrayAdapter<T> extends ArrayAdapter<String> {
    Context context;

    public LanguageArrayAdapter(Context context, int simple_list_item_1, String [] languages) {
        super(context, simple_list_item_1, languages);
        this.context = context;
    }

    public void setLocale(Context c, int i, Activity a) {
        Locale myLocale;
        switch (i) {
            case 1:
                myLocale = new Locale("mr");
                break;
            case 2:
                myLocale = new Locale("hi");
                break;
            default:
                myLocale = new Locale("en");
                break;
        }

        Resources res = a.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(a, a.getClass());
        a.finish();
        a.startActivity(refresh);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = super.getView(position, view, parent);
        TextView t;
        switch (position) {
            case 1:
                t = (TextView) view;
                t.setText(R.string.marathi);

                break;
            case 2:
                t = (TextView) view;
                t.setText(R.string.hindi);
                break;
            default:
                t = (TextView) view;
                t.setText(R.string.english);
                break;
        }
        return view;
    }
}
