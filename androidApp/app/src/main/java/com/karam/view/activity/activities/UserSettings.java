package com.karam.view.activity.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
        final ArrayAdapter<String> adapter = new UserSettingsAdapter<String>(this,
                android.R.layout.simple_list_item_1);
        ListView settingList = findViewById(R.id.settingsList);
        settingList.setAdapter(adapter);
        settingList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ((UserSettingsAdapter) adapter).showSettings(getApplicationContext(), i, UserSettings.this);
            }
        });

        final ArrayAdapter<String> ladapter = new LanguageArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1);
        ListView languageList = findViewById(R.id.languageList);
        languageList.setAdapter(ladapter);
        languageList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ((LanguageArrayAdapter) ladapter).setLocale(getApplicationContext(), i, UserSettings.this);
            }
        });
    }
}
