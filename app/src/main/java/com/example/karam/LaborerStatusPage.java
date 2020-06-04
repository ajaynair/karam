package com.example.karam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class LaborerStatusPage extends AppCompatActivity {
    private static String[] languages = {"English",
            "Hindi",
            "Marathi"};

    private static String[] friends = {"Self",
            "Mahesh",
            "Suresh"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.laborer_status_page);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        assignListenerToViews();
    }

    private void assignListenerToViews() {
        Button laborerReg = (Button) findViewById(R.id.newWorkRequest);
        laborerReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LaborerStatusPage.this, WorkRequestPage.class));
            }
        });
    }

    private ArrayAdapter<String> getAdapterWithLanguages() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, languages);

        return adapter;
    }

    private ArrayAdapter<String> getAdapterWithFriends() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, friends);

        return adapter;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.logout):
                startActivity(new Intent(LaborerStatusPage.this, LoginPage.class));
                break;
            case (R.id.user_settings):
                Toast.makeText(LaborerStatusPage.this, "Support not added", Toast.LENGTH_SHORT).show();
                break;
            case (R.id.check_status):
                startActivity(new Intent(LaborerStatusPage.this, LaborerStatusPage.class));
        }
        return true;
    }
}
