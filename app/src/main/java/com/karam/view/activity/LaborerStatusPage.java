package com.karam.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.karam.db.pojo.Laborer;
import com.karam.utils.BaseActivity;
import com.karam.view.restservice.RestService;
import com.karam.view.restservice.RetroFitService;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Page for the laborer to check their and their friend's job request status
 */
public class LaborerStatusPage extends BaseActivity {
    LaborerListAdapter adapter;
    ArrayList<Laborer> laborers;

    private void initLaborerStatus() throws IOException {
        initRecyclerView();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.laborer_status_page;
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
        // setContentView(R.layout.laborer_status_page);

        //Toolbar myToolbar = findViewById(R.id.my_toolbar);
        //setSupportActionBar(myToolbar);
        assignListenerToViews();
        try {
            initLaborerStatus();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            send_rest_request(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Assign all listener to different views of the view.activity
     */
    private void assignListenerToViews() {
        Button laborerReg = (Button) findViewById(R.id.newWorkRequest);
        laborerReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LaborerStatusPage.this, WorkRequestPage.class));
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_laborers, menu);
        return true;
    }
     */
    // TODO: This function can be moved to a separate menu class as its
    // used by all view.activity class

    /**
     * Set up menu options
     *
     * @param menu: Menu options (https://pasteboard.co/Jc4U58s.png) to be shown in the view.activity
     * @return: true on no error
    @Override
    public boolean onOptionsItemSelected(MenuItem menu) {
        switch (menu.getItemId()) {
            case (R.id.logout):
                startActivity(new Intent(LaborerStatusPage.this, LoginPage.class));
                break;
            case (R.id.check_status):
                startActivity(new Intent(LaborerStatusPage.this, LaborerStatusPage.class));
                break;
            case (R.id.about_us):
                startActivity(new Intent(LaborerStatusPage.this, AboutUs.class));
                break;
            case (R.id.user_settings):
                startActivity(new Intent(LaborerStatusPage.this, UserSettings.class));
                return true;
            default:
                Toast.makeText(getApplicationContext(), "Oops! Error. You shouldn't be seeing this message",
                        Toast.LENGTH_SHORT).show();
                // Add code to report bug
                break;
        }
        return true;
    }
     */
    void send_rest_request(int pid) throws IOException {
        try {
            RetroFitService retro = new RetroFitService(getApplicationContext());
            RestService service = retro.getService();

            Call<ArrayList<Laborer>> callSync = service.getLaborerFriends(pid);
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
