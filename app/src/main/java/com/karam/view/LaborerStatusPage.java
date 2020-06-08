package com.karam.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

/**
 * Page for the laborer to check their and their friend's job request status
 */
public class LaborerStatusPage extends AppCompatActivity {
    private static String[] languages = {"English",
            "Hindi",
            "Marathi"};

    private static String[] friends = {"Self",
            "Mahesh",
            "Suresh"};

    /**
     * Handle what happens when the activity is created
     * @param savedInstanceState: null for now
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.laborer_status_page);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        assignListenerToViews();
    }

    /**
     * Assign all listener to different views of the activity
     */
    private void assignListenerToViews() {
        Button laborerReg = (Button) findViewById(R.id.newWorkRequest);
        laborerReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LaborerStatusPage.this, WorkRequestPage.class));
            }
        });
    }

    // TODO: This function can be moved to a separate menu class as its
    // used by all activity class
    /**
     * Set up menu options
     * @param menu: Menu options (https://pasteboard.co/Jc4U58s.png) to be shown in the activity
     * @return: true on no error
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    // TODO: This function can be moved to a separate menu class as its
    // used by all activity class
    /**
     * Set up menu options
     * @param menu: Menu options (https://pasteboard.co/Jc4U58s.png) to be shown in the activity
     * @return: true on no error
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem menu) {
        switch (menu.getItemId()) {
            case (R.id.logout):
                startActivity(new Intent(LaborerStatusPage.this, LoginPage.class));
                break;
            case (R.id.user_settings):
                Toast.makeText(LaborerStatusPage.this, "Support not added", Toast.LENGTH_SHORT).show();
                break;
            case (R.id.check_status):
                startActivity(new Intent(LaborerStatusPage.this, LaborerStatusPage.class));
                break;
            default:
                Toast.makeText(getApplicationContext(), "Oops! Error. You shouldn't be seeing this message",
                        Toast.LENGTH_SHORT).show();
                // Add code to report bug
                break;
        }
        return true;
    }
}
