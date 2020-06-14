package com.karam.view.activity;

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
 * Main page for a user to register for a job request
 */
public class WorkRequestPage extends AppCompatActivity {

    /**
     * Handle what happens when the view.activity is created
     *
     * @param savedInstanceState: null for now
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.work_request_page);
        assignListenerToViews();
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    /**
     * Assign all listener to different views of the view.activity
     */
    private void assignListenerToViews() {
        Button selfReg = (Button) findViewById(R.id.RegisterForSelf);
        selfReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WorkRequestPage.this, WorkRequestSelf.class));
            }
        });
        Button friendReg = (Button) findViewById(R.id.RegisterForFriend);
        friendReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WorkRequestPage.this, WorkRequestFriend.class));
            }
        });
    }

    // TODO: This function can be moved to a separate menu class as its
    // used by all view.activity class

    /**
     * Set up menu options
     *
     * @param menu: Menu options (https://pasteboard.co/Jc4U58s.png) to be shown in the view.activity
     * @return: true on no error
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    // TODO: This function can be moved to a separate menu class as its
    // used by all view.activity class

    /**
     * Responds to menu option (https://pasteboard.co/Jc4U58s.png) of this view.activity
     *
     * @param item: The item in the menu that is selected
     * @return: return false in case of error, true otherwise
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.logout):
                startActivity(new Intent(WorkRequestPage.this, LoginPage.class));
                break;
            case (R.id.user_settings):
                Toast.makeText(WorkRequestPage.this, "Support not added", Toast.LENGTH_SHORT).show();
                break;
            case (R.id.check_status):
                startActivity(new Intent(WorkRequestPage.this, LaborerStatusPage.class));
                break;
            default:
                Toast.makeText(WorkRequestPage.this, "Oops! Error", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
