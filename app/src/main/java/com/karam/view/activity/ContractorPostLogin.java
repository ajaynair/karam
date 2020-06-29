package com.karam.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.karam.db.pojo.Contractor;
import com.karam.db.pojo.ErrorResponse;
import com.karam.db.pojo.Laborer;
import com.karam.view.restservice.RestService;
import com.karam.view.restservice.RetroFitService;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Contractor's landing page once they login
 * The contractor can search for laborers from this page
 */
public class ContractorPostLogin extends AppCompatActivity {

    LaborerListAdapter adapter;

    private void initLaborerStatus() throws IOException {
        initRecyclerView();
        send_rest_request();
    }

    private void initRecyclerView() {
        RecyclerView view = findViewById(R.id.recycler_view);
        view.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {
                    Toast.makeText(getApplicationContext(), "last", Toast.LENGTH_LONG);
                }
            }
        });
        ArrayList<Laborer> laborers = new ArrayList<>();
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
            setContentView(R.layout.contractor_post_login);
            assignListenerToViews();

            // Attach action bar to the view.activity
            Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
            setSupportActionBar(myToolbar);
            initLaborerStatus();
            }     catch (Exception ex) {
                    Toast.makeText(getApplicationContext(), ex.toString(),
                        Toast.LENGTH_LONG).show();
                    ex.printStackTrace();
        }

    }

    void send_rest_request() throws IOException {
        try {
            RetroFitService retro = new RetroFitService(getApplicationContext());
            RestService service = retro.getService();

            String[] skills = new String[]{"skill1", "skill2"};
            String[] locations = new String[]{"location1", "location2"};

            Call<ArrayList<Laborer>> callSync = service.getLaborers(skills, locations);
            Response<ArrayList<Laborer>> response = callSync.execute();
            ArrayList<Laborer> apiResponse = response.body();
            adapter.addItems(apiResponse);
            adapter.notifyDataSetChanged();
        }     catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.toString(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }

    /**
     * Assign all listener to different views of the view.activity
     */
    private void assignListenerToViews() {
        SearchView laborerSearch = (SearchView) findViewById(R.id.search);
        laborerSearch.setIconifiedByDefault(false);
        laborerSearch.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                try {
                    send_rest_request();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


    }

    // TODO: This function can be moved to a separate menu class as its
    // used by all view.activity class

    /**
     * Set up menu options
     *
     * @param menu: Menu options (https://pasteboard.co/Jc4U58s.png) to be shown in the view.activity
     * @return: true on no error
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contractor, menu);
        return true;
    }

    // TODO: This function can be moved to a separate menu class as its
    // used by all view.activity class

    /**
     * Responds to menu option (https://pasteboard.co/Jc4U58s.png) of this view.activity
     *
     * @param item: The item in the menu that is selected
     * @return: return false in case of error, true otherwise
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.logout):
                startActivity(new Intent(ContractorPostLogin.this, LoginPage.class));
                return true;
            case (R.id.about_us):
                startActivity(new Intent(ContractorPostLogin.this, AboutUs.class));
                return true;
            case (R.id.user_settings):
                startActivity(new Intent(ContractorPostLogin.this, UserSettings.class));
                return true;
            default:
                Toast.makeText(getApplicationContext(), "Oops! Error. You shouldn't be seeing this message",
                        Toast.LENGTH_SHORT).show();
                // Add code to report bug
                return false;
        }
    }
}
