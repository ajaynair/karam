package com.example.karam;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

// TODO: Check if this activity can be removed
/**
 *  Contractor's landing page once they login
 *  The contractor can search for laborers from this page
 */
public class ContractorPostLoginSearch extends AppCompatActivity {

    /**
     * Handle what happens when the activity is created
     * @param savedInstanceState: null for now
     */    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contractor_post_login_search);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
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
}
