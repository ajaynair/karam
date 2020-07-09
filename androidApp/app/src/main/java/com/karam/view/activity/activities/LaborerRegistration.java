package com.karam.view.activity.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.karam.rest.rest_messages.requests.Laborer;
import com.karam.rest.rest_messages.responses.Registration;
import com.karam.sharedPreference.UserData;
import com.karam.rest.RestServiceInterface;
import com.karam.rest.RestService;
import com.karam.view.activity.BaseActivity;
import com.karam.view.activity.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Page for the laborer to register themselves to the app
 */
public class LaborerRegistration extends BaseActivity {
    EditText name;
    EditText age;
    EditText address;
    RadioGroup aadharStatus;
    EditText phone;
    EditText password;
    Spinner location;
    Button register;
    UserData userData;

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

        name = findViewById(R.id.inputName);
        age = findViewById(R.id.inputAge);
        address = findViewById(R.id.inputAddress);
        aadharStatus = findViewById(R.id.radio_group);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        location = findViewById(R.id.spinner);
        userData = new UserData(this);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        location.setAdapter(adapter);
        assignListenerToViews();
    }

    private void send_rest_request() {
        RestService retro = new RestService(getApplicationContext());
        RestServiceInterface service = retro.getService();
        Toast.makeText(getApplicationContext(), age.getText(),
                Toast.LENGTH_SHORT).show();

        Laborer laborer = new Laborer(1, name.getText().toString(), name.getText().toString(), location.getSelectedItem().toString(), phone.getText().toString(), 23, "f", aadharStatus.getCheckedRadioButtonId() == R.id.yes ? "Y" : "N", "Carpentry");
        Call<Registration> callSync = service.registerAsLaborer(laborer);
        callSync.enqueue(new Callback<Registration>() {
            @Override
            public void onResponse(Call<Registration> call, Response<Registration> response) {
                Registration apiResponse = response.body();
                System.out.println(apiResponse);
                Toast.makeText(getApplicationContext(), apiResponse.toString(),
                        Toast.LENGTH_SHORT).show();
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
                userData.setUserStateContractor();
                startActivity(new Intent(LaborerRegistration.this, LaborerStatusPage.class));
            }
        });
        TextView nowork = findViewById(R.id.nowork);
        nowork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LaborerRegistration.this, LaborerStatusPage.class));
            }
        });
    }
}
