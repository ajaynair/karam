package com.karam.view;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Landing page of the app for a user who have not logged in
 */
public class LandingPage extends AppCompatActivity {
    private static String[] languages = {"English",
            "Hindi",
            "Marathi"};
    ArrayAdapter<String> adapter;

    /**
     * Handle what happens when the activity is created
     *
     * @param savedInstanceState: null for now
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);
        getAdapterWithLanguages();
        ListView languageList = findViewById(R.id.languageList);
        languageList.setAdapter(adapter);
        languageList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getApplicationContext(), (String) adapterView.getItemAtPosition(i) + " not available",
                //        Toast.LENGTH_SHORT).show();
                ((LanguageArrayAdapter)adapter).setLocale(getApplicationContext(), i, LandingPage.this);
            }
        });

        assignListenerToViews();
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    // TODO: This function can be moved to a separate menu class as its
    // used by all activity class

    /**
     * Set up menu options
     *
     * @param menu: Menu options (https://pasteboard.co/Jc4U58s.png) to be shown in the activity
     * @return: false so that menu option (3 dots) is not shown
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    /**
     * Assign all listener to different views of the activity
     */
    private void assignListenerToViews() {
        Button laborerReg = (Button) findViewById(R.id.RegisterForSelf);
        laborerReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LandingPage.this, LaborerRegistration.class));
            }
        });

        Button contractorReg = (Button) findViewById(R.id.RegisterForFriend);
        contractorReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LandingPage.this, ContractorRegistration.class));
            }
        });

        Button login = (Button) findViewById(R.id.loginButton);
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
    void getAdapterWithLanguages() {
        adapter = new LanguageArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, languages);
    }
}
