package com.karam.view.activity.common;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.karam.sharedPreference.UserData;
import com.karam.view.activity.R;
import com.karam.view.activity.activities.AboutUs;
import com.karam.view.activity.activities.ContractorPostLogin;
import com.karam.view.activity.activities.LaborerStatusPage;
import com.karam.view.activity.activities.LoginPage;
import com.karam.view.activity.activities.UserSettings;

public class BottomNavigation {
    private static String TAG = "BottomNavigation";
    Activity activity;
    UserData userData = null;

    public BottomNavigation(Activity activity) {
        this.activity = activity;
    }

    public void inflateBottomNavigation() {
        userData = new UserData(this.activity);

        BottomNavigationView bottomNavigation = this.activity.findViewById(R.id.bottom_navigation_common);
        if (bottomNavigation == null) {
            Log.e(TAG, "Bottom Navigation view not found. Check activity xml.");
            return;
        }

        if (userData.getUserState() == userData.getNotLoggedInValue())
            bottomNavigation.inflateMenu(R.menu.bottom_toolbar_menu_logged_out);
        else
            bottomNavigation.inflateMenu(R.menu.bottom_toolbar_menu_logged_in);


        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bottom_navigation_about_us:
                        BottomNavigation.this.activity.startActivity(new Intent(BottomNavigation.this.activity, AboutUs.class));
                        return true;
                    case R.id.bottom_navigation_home:
                        if (userData.getUserState() == userData.getContractorValue())
                            BottomNavigation.this.activity.startActivity(new Intent(BottomNavigation.this.activity, ContractorPostLogin.class));
                        else if (userData.getUserState() == userData.getLaborerValue())
                            BottomNavigation.this.activity.startActivity(new Intent(BottomNavigation.this.activity, LaborerStatusPage.class));
                        else
                            BottomNavigation.this.activity.startActivity(new Intent(BottomNavigation.this.activity, LoginPage.class));
                        return true;
                    case R.id.bottom_navigation_back:
                        BottomNavigation.this.activity.finish();
                        //BottomNavigation.this.activity.startActivity(new Intent(BottomNavigation.this.activity, AboutUs.class));
                        return true;
                    case R.id.bottom_navigation_settings:
                        BottomNavigation.this.activity.startActivity(new Intent(BottomNavigation.this.activity, UserSettings.class));
                        return true;
                }
                return true;
            }
        });
    }
}
