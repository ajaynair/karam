package com.karam.view.activity.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.karam.rest.RestClient;
import com.karam.rest.RestClientInterface;
import com.karam.rest.rest_messages.requests.Contractor;
import com.karam.rest.rest_messages.responses.Registration;
import com.karam.sharedPreference.UserData;
import com.karam.view.activity.BaseActivity;
import com.karam.view.activity.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Page for the contractor to register themselves to the app
 */
public class ContractorRegistration extends BaseActivity {
    EditText name;
    EditText phone;
    EditText address;
    EditText username;
    EditText password;
    Button register;

    /**
     * Handle what happens when the view.activity is created
     *
     * @param savedInstanceState: null for now
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        name = findViewById(R.id.inputName);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.inputAddress);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);

        assignListenerToViews();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.contractor_registration;
    }

    private void send_rest_request() {
        RestClient retro = new RestClient(getApplicationContext());
        RestClientInterface service = retro.getService();

        Contractor contractor = new Contractor(name.getText().toString(),
                address.getText().toString(),
                phone.getText().toString(),
                username.getText().toString(),
                password.getText().toString());
        Call<Registration> callSync = service.registerAsContractor(contractor);
        callSync.enqueue(new Callback<Registration>() {
            @Override
            public void onResponse(Call<Registration> call, Response<Registration> response) {
                Registration apiResponse = response.body();
                userData.set_user_id(apiResponse.getUserId());
                userData.setUserStateContractor();
                startActivity(new Intent(ContractorRegistration.this, ContractorPostLogin.class));
            }

            @Override
            public void onFailure(Call<Registration> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Assign all listener to different views of the view.activity
     */
    private void assignListenerToViews() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(name.getText()) ||
                        TextUtils.isEmpty(phone.getText()) ||
                        TextUtils.isEmpty(address.getText()) ||
                        TextUtils.isEmpty(username.getText()) ||
                        TextUtils.isEmpty(password.getText())) {
                    Toast.makeText(getApplicationContext(), "Please fill all the fields",
                            Toast.LENGTH_SHORT).show();
                } else {
                    send_rest_request();
                }
            }
        });
    }
}
