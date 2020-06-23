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

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.karam.view.LanguageArrayAdapter;
import com.karam.view.UserSettingsAdapter;

public class UserSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_settings);
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

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        assignListenerToViews();
    }

    /**
     * Assign all listener to different views of the view.activity
     */
    private void assignListenerToViews() {
        Button laborerReg = (Button) findViewById(R.id.button_home);
        laborerReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserSettings.this, LaborerStatusPage.class));
            }
        });
    }

    /**
     * Set up menu options
     *
     * @param menu: Menu options (https://pasteboard.co/Jc4U58s.png) to be shown in the view.activity
     * @return: true on no error
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_logged_out, menu);
        return true;
    }

    /**
     * Responds to menu option (https://pasteboard.co/Jc4U58s.png) of this view.activity
     *
     * @param item: The item in the menu that is selected
     * @return: return false in case of error, true otherwise
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.about_us):
                startActivity(new Intent(UserSettings.this, AboutUs.class));
                return true;
            default:
                Toast.makeText(getApplicationContext(), "Oops! Error. You shouldn't be seeing this message",
                        Toast.LENGTH_SHORT).show();
                // Add code to report bug
                return false;
        }
    }
}
