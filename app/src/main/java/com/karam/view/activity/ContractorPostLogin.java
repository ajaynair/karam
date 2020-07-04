package com.karam.view.activity;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.karam.db.pojo.Laborer;
import com.karam.utils.BaseActivity;
import com.karam.view.restservice.RestService;
import com.karam.view.restservice.RetroFitService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Contractor's landing page once they login
 * The contractor can search for laborers from this page
 */
public class ContractorPostLogin extends BaseActivity {

    LaborerListAdapter adapter;
    ArrayList<Laborer> laborers;
    RecyclerView view;
    SearchView laborerSearch;

    private void initLaborerStatus() {
        initRecyclerView();
    }

    private void fillViews() {
        view = findViewById(R.id.recycler_view);
        laborerSearch = findViewById(R.id.search);
    }

    private void initRecyclerView() {
        view.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {
                    Toast.makeText(getApplicationContext(), "last", Toast.LENGTH_LONG);
                }
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
        try {
            super.onCreate(savedInstanceState);
            fillViews();
            assignListenerToViews();
            initLaborerStatus();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.toString(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.contractor_post_login;
    }

    void send_rest_request(String query) {
        try {
            RetroFitService retro = new RetroFitService(getApplicationContext());
            RestService service = retro.getService();

            String[] skills = new String[]{query};
            String[] locations = new String[]{query};

            Call<ArrayList<Laborer>> callSync = service.getLaborers(skills, locations);
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

    /**
     * Assign all listener to different views of the view.activity
     */
    private void assignListenerToViews() {
        laborerSearch.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                send_rest_request(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}
