package com.karam.view.activity.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.karam.rest.RestClient;
import com.karam.rest.RestClientInterface;
import com.karam.rest.rest_messages.requests.Laborer;
import com.karam.rest.rest_messages.responses.Registration;
import com.karam.view.activity.BaseActivity;
import com.karam.view.activity.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Page for the laborer to register themselves to the app
 */
public class LaborerRegistration extends BaseActivity {
    private EditText fname;
    private EditText lname;
    private EditText age;
    private EditText address;
    private RadioGroup aadharStatus;
    private EditText phone;
    private EditText username;
    private EditText password;
    private EditText skills;
    private EditText gender;
    private Spinner location;
    private Button register;

    @Override
    protected int getLayoutResource() {
        return R.layout.laborer_registration;
    }

    /**
     * Handle what happens when the view.activity is created
     *
     * @param savedInstanceState: null for now
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.locations, android.R.layout.simple_spinner_item);

        fname = findViewById(R.id.fName);
        lname = findViewById(R.id.lName);
        age = findViewById(R.id.inputAge);
        address = findViewById(R.id.inputAddress);
        aadharStatus = findViewById(R.id.radio_group);
        phone = findViewById(R.id.phone);
        gender = findViewById(R.id.gender);
        skills = findViewById(R.id.skills);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        location = findViewById(R.id.spinner);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        location.setAdapter(adapter);
        assignListenerToViews();
    }

    private void send_rest_request() {
        RestClient retro = new RestClient(getApplicationContext());
        RestClientInterface service = retro.getService();

        int i_age = 0;
        if (!age.getText().toString().equals("")) {
            i_age = Integer.parseInt(age.getText().toString());
        }
        // TODO NEW_COMER Get values that are hardcoded from the UI and fill it
        Laborer laborer = new Laborer(0, fname.getText().toString(), lname.getText().toString(), location.getSelectedItem().toString(), phone.getText().toString(), i_age, gender.getText().toString(), aadharStatus.getCheckedRadioButtonId() == R.id.yes ? "Yes" : "No", skills.getText().toString(), username.getText().toString(), password.getText().toString());
        Call<Registration> callSync = service.registerAsLaborer(laborer);
        callSync.enqueue(new Callback<Registration>() {
            @Override
            public void onResponse(Call<Registration> call, Response<Registration> response) {
                Registration apiResponse = response.body();

                userData.set_user_id(apiResponse.getUserId());
                userData.setUserStateLaborer();
                startActivity(new Intent(LaborerRegistration.this, LaborerStatusPage.class));
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
        Button laborerReg = findViewById(R.id.register);
        laborerReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(fname.getText()) ||
                        TextUtils.isEmpty(lname.getText()) ||
                        TextUtils.isEmpty(age.getText()) ||
                        TextUtils.isEmpty(address.getText()) ||
                        TextUtils.isEmpty(phone.getText()) ||
                        TextUtils.isEmpty(skills.getText()) ||
                        TextUtils.isEmpty(gender.getText()) ||
                        TextUtils.isEmpty(username.getText()) ||
                        TextUtils.isEmpty(password.getText()) ||
                        aadharStatus.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getApplicationContext(), "Please fill all the fields",
                            Toast.LENGTH_SHORT).show();
                } else {
                    send_rest_request();
                }
            }
        });
    }
}
