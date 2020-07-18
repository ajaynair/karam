package com.karam.view.activity.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.karam.adapter.LaborerListAdapter;
import com.karam.rest.RestClient;
import com.karam.rest.RestClientInterface;
import com.karam.rest.rest_messages.requests.Laborer;
import com.karam.view.activity.BaseActivity;
import com.karam.view.activity.R;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Page for the laborer to check their and their friend's job request status
 */
public class LaborerStatusPage extends BaseActivity {
    private LaborerListAdapter adapter;
    private ArrayList<Laborer> laborers;

    private void initLaborerStatus() {
        initRecyclerView();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.laborer_status_page;
    }

    private void initRecyclerView() {
        RecyclerView view = findViewById(R.id.contractor_post_login_recycler_view);
        view.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        laborers = new ArrayList<>();
        adapter = new LaborerListAdapter(laborers, this);
        view.setAdapter(adapter);
        view.setLayoutManager(new LinearLayoutManager(this));
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
        initLaborerStatus();
        send_rest_request(userData.get_user_id());
    }

    /**
     * Assign all listener to different views of the view.activity
     */
    private void assignListenerToViews() {
        Button laborerReg = findViewById(R.id.newWorkRequest);
        laborerReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LaborerStatusPage.this, WorkRequestPage.class));
            }
        });
    }

    private void send_rest_request(int pid) {
        try {
            RestClient retro = new RestClient(getApplicationContext());
            RestClientInterface service = retro.getService();

            Call<ArrayList<Laborer>> callSync = service.getLaborerAndFriendsProfile(pid);
            Response<ArrayList<Laborer>> response = callSync.execute();
            ArrayList<Laborer> apiResponse = response.body();
            if (laborers != null)
                laborers.clear();
            laborers.addAll(apiResponse);
            adapter.notifyDataSetChanged();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.toString(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }
}
