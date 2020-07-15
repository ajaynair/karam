package com.karam.view.activity.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.karam.adapter.LanguageArrayAdapter;
import com.karam.adapter.UserSettingsAdapter;
import com.karam.view.activity.BaseActivity;
import com.karam.view.activity.R;

public class UserSettings extends BaseActivity {

    @Override
    protected int getLayoutResource() {
        return R.layout.user_settings;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ArrayAdapter<String> settings_adapter = new UserSettingsAdapter<String>(this,
                android.R.layout.simple_list_item_1);
        ListView settingList = findViewById(R.id.settingsList);
        settingList.setAdapter(settings_adapter);
        settingList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ((UserSettingsAdapter) settings_adapter).showSettings(getApplicationContext(), i, UserSettings.this);
            }
        });

        final ArrayAdapter<String> language_adapter = new LanguageArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1);
        ListView languageList = findViewById(R.id.languageList);
        languageList.setAdapter(language_adapter);
        languageList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ((LanguageArrayAdapter) language_adapter).setLocale(getApplicationContext(), i, UserSettings.this, userData);
            }
        });
    }
}
