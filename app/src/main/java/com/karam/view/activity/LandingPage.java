package com.karam.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.karam.utils.BaseActivity;
import com.karam.view.LanguageArrayAdapter;


/**
 * Landing page of the app for a user who have not logged in
 */
public class LandingPage extends BaseActivity {

    ArrayAdapter<String> adapter;

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
        Toast.makeText(this, "wtf", Toast.LENGTH_LONG);
        getAdapterWithLanguages();

        ListView languageList = findViewById(R.id.languageList);
        languageList.setAdapter(adapter);
        languageList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ((LanguageArrayAdapter) adapter).setLocale(getApplicationContext(), i, LandingPage.this);
            }
        });

        assignListenerToViews();
        //Toolbar myToolbar = findViewById(R.id.my_toolbar);
        //setSupportActionBar(myToolbar);
        Toast.makeText(this, "wtf", Toast.LENGTH_LONG);
    }

    /**
     * Assign all listener to different views of the view.activity
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
                android.R.layout.simple_list_item_1);
    }

    /**
     * Set up menu options
     *
     * @param menu: Menu options (https://pasteboard.co/Jc4U58s.png) to be shown in the view.activity
     * @return: true on no error

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_logged_out, menu);
        return true;
    }
     */
    /**
     * Responds to menu option (https://pasteboard.co/Jc4U58s.png) of this view.activity
     *
     * @param item: The item in the menu that is selected
     * @return: return false in case of error, true otherwise

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.about_us):
                startActivity(new Intent(LandingPage.this, AboutUs.class));
                return true;
            default:
                Toast.makeText(getApplicationContext(), "Oops! Error. You shouldn't be seeing this message",
                        Toast.LENGTH_SHORT).show();
                // Add code to report bug
                return false;
        }
    }
     */
}
