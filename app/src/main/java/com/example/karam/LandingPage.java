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
import android.widget.Toast;

public class LandingPage extends AppCompatActivity {
    private static String[] languages = {"English",
            "Hindi",
            "Marathi"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);
        ArrayAdapter<String> adapter = getAdapterWithLanguages();
        ListView languageList = (ListView) findViewById(R.id.languageList);
        languageList.setAdapter(adapter);
        languageList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), (String)adapterView.getItemAtPosition(i) + " not available",
                        Toast.LENGTH_SHORT).show();
            }
        });

        assignListenerToViews();
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    private void assignListenerToViews() {
        Button laborerReg = (Button) findViewById(R.id.RegisterForSelf);
        laborerReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LandingPage.this, LaborerRegistration.class));
            }
        });

        Button contractorReg = (Button) findViewById(R.id.RegisterForFriend);
        contractorReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LandingPage.this, ContractorRegistration.class));
            }
        });

        Button login = (Button) findViewById(R.id.loginButton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LandingPage.this, LoginPage.class));
            }
        });
    }

    private ArrayAdapter<String> getAdapterWithLanguages() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, languages);

        return adapter;
    }

    public View.OnClickListener onClick(View view) {
        Toast.makeText(LandingPage.this, "Button Clicked", Toast.LENGTH_SHORT).show();
        return null;
    }
}
