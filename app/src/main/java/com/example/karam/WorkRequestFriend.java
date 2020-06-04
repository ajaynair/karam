package com.example.karam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class WorkRequestFriend extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.work_request_friend);
        assignListenerToViews();
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    private void assignListenerToViews() {
        Button laborerReg = (Button) findViewById(R.id.register);
        laborerReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WorkRequestFriend.this, LaborerStatusPage.class));
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.logout):
                startActivity(new Intent(WorkRequestFriend.this, LoginPage.class));
                break;
            case (R.id.user_settings):
                Toast.makeText(WorkRequestFriend.this, "Support not added", Toast.LENGTH_SHORT).show();
                break;
            case (R.id.check_status):
                startActivity(new Intent(WorkRequestFriend.this, LaborerStatusPage.class));
        }
        return true;
    }
}
