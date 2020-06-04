package com.example.karam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

public class ContractorPostLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contractor_post_login);
        assignListenerToViews();
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    private void assignListenerToViews() {
        SearchView laborerSearch = (SearchView) findViewById(R.id.search);
        laborerSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ContractorPostLogin.this, ContractorPostLoginSearch.class));
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        menu.removeItem(R.id.user_settings);
        menu.removeItem(R.id.check_status);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.logout):
                startActivity(new Intent(ContractorPostLogin.this, LoginPage.class));
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        Toast.makeText(getApplicationContext(), "back pressed", Toast.LENGTH_SHORT).show();
    }
}
