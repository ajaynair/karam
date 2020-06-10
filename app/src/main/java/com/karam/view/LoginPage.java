package com.karam.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

/**
 * Page for a registered user to login
 */
public class LoginPage extends AppCompatActivity {

    /**
     * Handle what happens when the activity is created
     * @param savedInstanceState: null for now
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        assignListenerToViews();
    }

    /**
     * Assign all listener to different views of the activity
     */
    private void assignListenerToViews() {
        Button laborerReg = (Button) findViewById(R.id.loginButton);
        laborerReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginPage.this, ContractorPostLogin.class));
            }
        });
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    // TODO: This function can be moved to a separate menu class as its
    // used by all activity class
    /**
     * Set up menu options
     * @param menu: Menu options (https://pasteboard.co/Jc4U58s.png) to be shown in the activity
     * @return: false so that menu option (3 dots) is not shown
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }
}
