package com.karam.view.activity.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.karam.rest.RestClient;
import com.karam.rest.RestClientInterface;
import com.karam.rest.rest_messages.requests.Credentials;
import com.karam.rest.rest_messages.responses.Session;
import com.karam.view.activity.BaseActivity;
import com.karam.view.activity.R;

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
    TextView wrongUsrPwd;
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

        name = findViewById(R.id.loginUserNameText);
        password = findViewById(R.id.loginPasswordText);
        login = findViewById(R.id.loginButton);
        wrongUsrPwd = findViewById(R.id.wrongUsePwd);

        assignListenerToViews();
    }

    private void send_rest_request(String username, String password) {
        RestClient retro = new RestClient(getApplicationContext());
        RestClientInterface service = retro.getService();

        Credentials credentials = new Credentials(username, password);
        Call<Session> callSync = service.login(credentials);
        callSync.enqueue(new Callback<Session>() {
            @Override
            public void onResponse(Call<Session> call, Response<Session> response) {
                Session apiResponse = response.body();

                Toast.makeText(getApplicationContext(), apiResponse.getRole_type(),
                        Toast.LENGTH_SHORT).show();
                // TODO Use enum for laborer and contractor
                if (apiResponse.getRole_type().equals("L")) {
                    userData.setUserStateLaborer();
                    wrongUsrPwd.setVisibility(View.INVISIBLE);
                    startActivity(new Intent(LoginPage.this, LaborerStatusPage.class));
                } else if (apiResponse.getRole_type().equals("C")) {
                    userData.setUserStateContractor();
                    wrongUsrPwd.setVisibility(View.INVISIBLE);
                    startActivity(new Intent(LoginPage.this, ContractorPostLogin.class));
                } else {
                    wrongUsrPwd.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<Session> call, Throwable t) {
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
    }
}
