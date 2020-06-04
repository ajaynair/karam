package com.example.karam;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

/**
 *  Contractor's landing page once they login
 *  The contractor can search for laborers from this page
 */
public class ContractorPostLogin extends AppCompatActivity {

    /**
     * Handle what happens when the activity is created
     * @param savedInstanceState: null for now
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contractor_post_login);
        assignListenerToViews();

        // Attach action bar to the activity
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    /**
     * Assign all listener to different views of the activity
     */
    private void assignListenerToViews() {
        SearchView laborerSearch = (SearchView) findViewById(R.id.search);
        laborerSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ContractorPostLogin.this, ContractorPostLoginSearch.class));
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
        menu.removeItem(R.id.user_settings);
        menu.removeItem(R.id.check_status);
        return true;
    }

    // TODO: This function can be moved to a separate menu class as its
    // used by all activity class
    /**
     *  Responds to menu option (https://pasteboard.co/Jc4U58s.png) of this activity
     * @param item: The item in the menu that is selected
     * @return: return false in case of error, true otherwise
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.logout):
                startActivity(new Intent(ContractorPostLogin.this, LoginPage.class));
                return true;
            default:
                Toast.makeText(getApplicationContext(), "Oops! Error. You shouldn't be seeing this message",
                        Toast.LENGTH_SHORT).show();
                // Add code to report bug
                return false;
        }
    }

    // TODO: The back button does not work for this page. Fix the issue.
    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "back pressed", Toast.LENGTH_SHORT).show();
    }
}
