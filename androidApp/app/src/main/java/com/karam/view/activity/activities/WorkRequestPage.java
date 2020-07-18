package com.karam.view.activity.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.karam.view.activity.BaseActivity;
import com.karam.view.activity.R;

/**
 * Main page for a user to register for a job request
 */
public class WorkRequestPage extends BaseActivity {

    @Override
    protected int getLayoutResource() {
        return R.layout.work_request_page;
    }

    /**
     * Handle what happens when the view.activity is created
     *
     * @param savedInstanceState: null for now
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        assignListenerToViews();
    }

    /**
     * Assign all listener to different views of the view.activity
     */
    private void assignListenerToViews() {
        Button selfReg = findViewById(R.id.RegisterForSelf);
        selfReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WorkRequestPage.this, WorkRequestSelf.class));
            }
        });
        Button friendReg = findViewById(R.id.RegisterForFriend);
        friendReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WorkRequestPage.this, WorkRequestFriend.class));
            }
        });
    }
}
