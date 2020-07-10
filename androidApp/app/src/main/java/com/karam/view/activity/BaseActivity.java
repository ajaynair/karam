package com.karam.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.karam.view.activity.common.BottomNavigation;
import com.karam.view.activity.common.TopNavigation;
import com.karam.sharedPreference.UserData;
import com.karam.view.activity.activities.AboutUs;
import com.karam.view.activity.activities.LoginPage;
import com.karam.view.activity.activities.UserSettings;

public abstract class BaseActivity extends AppCompatActivity {
    /**
     * Handle what happens when any activity is created
     *
     * @param savedInstanceState: null for now
     */
    protected UserData userData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());

        // TODO NEW_COMER Remove these 2 lines and implement threadpool
        /**
         * By default android does not allow running network operations (For e.g. REST calls to the server)
         * in the main thread. I think this is to make sure that the UI remains interactive.
         * The following 2 lines are *temporarily* added to disable this check. To solve the issue there are 2
         * ways I can think of:
         * 1. Create a new threadpool, use threads from the threadpool for all the network related operations
         * 2. Use android service for all network related operations
         */
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        userData = new UserData(this);
        BottomNavigation b = new BottomNavigation(this);
        b.inflateBottomNavigation();

        TopNavigation t = new TopNavigation(this);
        t.inflateTopNavigation();
    }

    protected abstract int getLayoutResource();

    /**
     * Set up menu options
     *
     * @param menu: Menu options (https://pasteboard.co/Jc4U58s.png) to be shown in the view.activity
     * @return: true on no error
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (userData.getUserState() == userData.getNotLoggedInValue())
            return false;
        getMenuInflater().inflate(R.menu.top_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.logout):
                userData.setUserStateLoggedOut();
                startActivity(new Intent(this, LoginPage.class));
                return true;
            default:
                Toast.makeText(this, "Oops! Error. You shouldn't be seeing this message",
                        Toast.LENGTH_SHORT).show();
                return false;
        }
    }
}
