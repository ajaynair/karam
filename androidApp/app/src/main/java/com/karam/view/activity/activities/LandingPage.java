package com.karam.view.activity.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.karam.adapter.LanguageArrayAdapter;
import com.karam.view.activity.BaseActivity;
import com.karam.view.activity.R;


/**
 * Landing page of the app for a user who have not logged in
 */
public class LandingPage extends BaseActivity {

    private ArrayAdapter<String> adapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.landing_page;
    }

    /**
     * Handle what happens when the view.activity is created
     *
     * @param savedInstanceState: null for now
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getAdapterWithLanguages();

        ListView languageList = findViewById(R.id.languageList);
        languageList.setAdapter(adapter);
        languageList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ((LanguageArrayAdapter) adapter).setLocale(getApplicationContext(), i, LandingPage.this, userData);
            }
        });

        assignListenerToViews();
    }

    /**
     * Assign all listener to different views of the view.activity
     */
    private void assignListenerToViews() {
        Button laborerReg = findViewById(R.id.RegisterForSelf);
        laborerReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LandingPage.this, LaborerRegistration.class));
            }
        });

        Button contractorReg = findViewById(R.id.RegisterForFriend);
        contractorReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LandingPage.this, ContractorRegistration.class));
            }
        });

        Button login = findViewById(R.id.loginButton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LandingPage.this, LoginPage.class));
            }
        });
    }

    /**
     * Create an adapter for language option for the user
     *
     * @return: adapter that contains all the languages
     */
    private void getAdapterWithLanguages() {
        adapter = new LanguageArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1);
    }

}
