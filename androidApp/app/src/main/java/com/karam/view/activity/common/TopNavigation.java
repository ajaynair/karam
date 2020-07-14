package com.karam.view.activity.common;

import android.content.Intent;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.karam.sharedPreference.UserData;
import com.karam.view.activity.R;
import com.karam.view.activity.activities.LoginPage;

public class TopNavigation {
    private static String TAG = "TopNavigation";
    AppCompatActivity activity;
    //UserData userData = null;

    public TopNavigation(AppCompatActivity activity) {
        this.activity = activity;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.logout):
                this.activity.startActivity(new Intent(this.activity, LoginPage.class));
                return true;
/*
            case (R.id.about_us):
                this.activity.startActivity(new Intent(this.activity, AboutUs.class));
                return true;
            case (R.id.user_settings):
                this.activity.startActivity(new Intent(this.activity, UserSettings.class));
                return true;
*/
            default:
                Toast.makeText(this.activity, "Oops! Error. You shouldn't be seeing this message",
                        Toast.LENGTH_SHORT).show();
                return false;
        }
    }

    public void inflateTopNavigation(UserData userData) {
        Toolbar myToolbar = this.activity.findViewById(R.id.top_toolbar_common);
        this.activity.setSupportActionBar(myToolbar);
    }
}
