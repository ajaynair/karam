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

public class WorkRequestPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.work_request_page);
        assignListenerToViews();
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    private void assignListenerToViews() {
        Button selfReg = (Button) findViewById(R.id.RegisterForSelf);
        selfReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WorkRequestPage.this, WorkRequestSelf.class));
            }
        });
        Button friendReg = (Button) findViewById(R.id.RegisterForFriend);
        friendReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WorkRequestPage.this, WorkRequestFriend.class));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.logout):
                startActivity(new Intent(WorkRequestPage.this, LoginPage.class));
                break;
            case (R.id.user_settings):
                Toast.makeText(WorkRequestPage.this, "Support not added", Toast.LENGTH_SHORT).show();
                break;
            case (R.id.check_status):
                startActivity(new Intent(WorkRequestPage.this, LaborerStatusPage.class));
        }
        return true;
    }
}
