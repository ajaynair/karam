package com.karam.view.activity.activities;

import android.content.Intent;
import android.os.Bundle;
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
 * Page for a user to register their friend as a laborer for a job request
 */
public class WorkRequestFriend extends BaseActivity {
    EditText fname;
    EditText lname;
    EditText age;
    EditText address;
    RadioGroup aadharStatus;
    EditText phone;
    EditText password;
    EditText skills;
    Spinner location;
    Button register;

    EditText username;

    EditText gender;


    @Override
    protected int getLayoutResource() {
        return R.layout.work_request_friend;
    }

    /**
     * Handle what happens when the view.activity is created
     *
     * @param savedInstanceState: null for now
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fname = findViewById(R.id.fName);
        lname = findViewById(R.id.lName);
        gender = findViewById(R.id.gender);
        age = findViewById(R.id.inputAge);
        address = findViewById(R.id.inputAddress);
        aadharStatus = findViewById(R.id.radio_group);
        phone = findViewById(R.id.phone);
        skills = findViewById(R.id.skills);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        location = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.locations, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        location.setAdapter(adapter);

        assignListenerToViews();
    }

    private void send_rest_request() {
        RestClient retro = new RestClient(getApplicationContext());
        RestClientInterface service = retro.getService();

        int i_age = 0;
        if (age.getText().toString().equals("") == false) {
            i_age = Integer.valueOf(age.getText().toString());
        }
        Laborer laborer = new Laborer(userData.get_user_id(), fname.getText().toString(), lname.getText().toString(), location.getSelectedItem().toString(), phone.getText().toString(), i_age, gender.getText().toString(), aadharStatus.getCheckedRadioButtonId() == R.id.yes ? "Yes" : "No", skills.getText().toString(), username.getText().toString(), password.getText().toString());
        Call<Registration> callSync = service.registerAsLaborer(laborer);
        callSync.enqueue(new Callback<Registration>() {
            @Override
            public void onResponse(Call<Registration> call, Response<Registration> response) {
                Registration apiResponse = response.body();
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
                send_rest_request();
                startActivity(new Intent(WorkRequestFriend.this, LaborerStatusPage.class));
            }
        });
    }
}
