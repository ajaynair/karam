package com.karam.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.karam.db.pojo.Credentials;
import com.karam.db.pojo.ErrorResponse;
import com.karam.db.pojo.Session;
import com.karam.view.restservice.RestService;
import com.karam.view.restservice.RetroFitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Page for a registered user to login
 */
public class LoginPage extends AppCompatActivity {
    EditText name;
    EditText password;
    Button login;

    /**
     * Handle what happens when the view.activity is created
     *
     * @param savedInstanceState: null for now
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        name = findViewById(R.id.loginUserNameText);
        password = findViewById(R.id.loginPasswordText);
        login = findViewById(R.id.loginButton);

        assignListenerToViews();
    }

    private void send_rest_request() {
        RetroFitService retro = new RetroFitService(getApplicationContext());
        RestService service = retro.getService();

        Credentials credentials = new Credentials("a", "b");
        Call<Session> callSync = service.createSession(credentials);
        callSync.enqueue(new Callback<Session>() {
            @Override
            public void onResponse(Call<Session> call, Response<Session> response) {
                Session apiResponse = response.body();
                System.out.println(apiResponse);
                Toast.makeText(getApplicationContext(), apiResponse.toString(),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Session> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    View.OnClickListener loginClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            send_rest_request();
            if (name.getText().toString().equals("l")) {
                Toast.makeText(getApplicationContext(), "laborer selected",
                        Toast.LENGTH_SHORT).show();

                startActivity(new Intent(LoginPage.this, LaborerStatusPage.class));
            } else {
                startActivity(new Intent(LoginPage.this, ContractorPostLogin.class));
            }
        }
    };

    /**
     * Assign all listener to different views of the view.activity
     */
    private void assignListenerToViews() {
        login.setOnClickListener(loginClickListener);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    /**
     * Set up menu options
     *
     * @param menu: Menu options (https://pasteboard.co/Jc4U58s.png) to be shown in the view.activity
     * @return: true on no error
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_logged_out, menu);
        return true;
    }

    /**
     * Responds to menu option (https://pasteboard.co/Jc4U58s.png) of this view.activity
     *
     * @param item: The item in the menu that is selected
     * @return: return false in case of error, true otherwise
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.about_us):
                startActivity(new Intent(LoginPage.this, AboutUs.class));
                return true;
            default:
                Toast.makeText(getApplicationContext(), "Oops! Error. You shouldn't be seeing this message",
                        Toast.LENGTH_SHORT).show();
                // Add code to report bug
                return false;
        }
    }
}
