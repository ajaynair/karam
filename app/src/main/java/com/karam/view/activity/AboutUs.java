package com.karam.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.karam.utils.BaseActivity;

public class AboutUs extends BaseActivity {
    private Button laborerReg = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fillViews();
        assignListenerToViews();
    }

    private void fillViews() {
        laborerReg = findViewById(R.id.button_home);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.about_us;
    }

    /**
     * Assign all listener to different views of the view.activity
     */
    private void assignListenerToViews() {
        laborerReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AboutUs.this, LaborerStatusPage.class));
            }
        });
    }
}
