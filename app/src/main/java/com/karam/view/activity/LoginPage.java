package com.karam.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.karam.db.pojo.Credentials;
import com.karam.db.pojo.LoginResponse;
import com.karam.utils.BaseActivity;
import com.karam.view.restservice.RestService;
import com.karam.view.restservice.RetroFitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Page for a registered user to login
 */
public class LoginPage extends BaseActivity {
    EditText name;
    EditText password;
    Button login;
    View.OnClickListener loginClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            send_rest_request(name.getText().toString(), password.getText().toString());
        }
    };

    @Override
    protected int getLayoutResource() {
        return R.layout.login_page;
    }

    /**
     * Handle what happens when the view.activity is created
     *
     * @param savedInstanceState: null for now
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.login_page);

        name = findViewById(R.id.loginUserNameText);
        password = findViewById(R.id.loginPasswordText);
        login = findViewById(R.id.loginButton);

        assignListenerToViews();
    }

    private void send_rest_request(String username, String password) {
        RetroFitService retro = new RetroFitService(getApplicationContext());
        RestService service = retro.getService();

        Credentials credentials = new Credentials(username, password);
        Call<LoginResponse> callSync = service.createSession(credentials);
        callSync.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse apiResponse = response.body();
                System.out.println(apiResponse);
                Toast.makeText(getApplicationContext(), apiResponse.toString(),
                        Toast.LENGTH_SHORT).show();
                if (apiResponse.getRole_type().equals("laborer")) {
                    userData.setUserStateLaborer();
                    startActivity(new Intent(LoginPage.this, LaborerStatusPage.class));
                }
                else {
                    userData.setUserStateContractor();
                    startActivity(new Intent(LoginPage.this, ContractorPostLogin.class));
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(),
                        Toast.LENGTH_SHORT).show();
                return;
            }
        });

    }

    /**
     * Assign all listener to different views of the view.activity
     */
    private void assignListenerToViews() {
        login.setOnClickListener(loginClickListener);
        //Toolbar myToolbar = findViewById(R.id.my_toolbar);
        //setSupportActionBar(myToolbar);
    }

    /**
     * Set up menu options
     *
     * @param menu: Menu options (https://pasteboard.co/Jc4U58s.png) to be shown in the view.activity
     * @return: true on no error

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_logged_out, menu);
        return true;
    }
     */
    /**
     * Responds to menu option (https://pasteboard.co/Jc4U58s.png) of this view.activity
     *
     * @param item: The item in the menu that is selected
     * @return: return false in case of error, true otherwise

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
     */
}
