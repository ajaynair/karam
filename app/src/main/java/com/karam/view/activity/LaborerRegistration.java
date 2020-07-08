package com.karam.view.activity;

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

import com.karam.db.pojo.Laborer;
import com.karam.db.pojo.LaborerRegistrationResponse;
import com.karam.utils.BaseActivity;
import com.karam.utils.UserData;
import com.karam.view.restservice.RestService;
import com.karam.view.restservice.RetroFitService;

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
        RetroFitService retro = new RetroFitService(getApplicationContext());
        RestService service = retro.getService();
        Toast.makeText(getApplicationContext(), age.getText(),
                Toast.LENGTH_SHORT).show();

        Laborer laborer = new Laborer(1, name.getText().toString(), name.getText().toString(), location.getSelectedItem().toString(), phone.getText().toString(), 23, "f", aadharStatus.getCheckedRadioButtonId() == R.id.yes? "Y":"N", "Carpentry");
        Call<LaborerRegistrationResponse> callSync = service.createLaborer(laborer);
        callSync.enqueue(new Callback<LaborerRegistrationResponse>() {
            @Override
            public void onResponse(Call<LaborerRegistrationResponse> call, Response<LaborerRegistrationResponse> response) {
                LaborerRegistrationResponse apiResponse = response.body();
                System.out.println(apiResponse);
                Toast.makeText(getApplicationContext(), apiResponse.toString(),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<LaborerRegistrationResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Assign all listener to different views of the view.activity
     */
    private void assignListenerToViews() {
        Button laborerReg = (Button) findViewById(R.id.register);
        laborerReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send_rest_request();
                userData.setUserStateContractor();
                startActivity(new Intent(LaborerRegistration.this, LaborerStatusPage.class));
            }
        });
        TextView nowork = (TextView) findViewById(R.id.nowork);
        nowork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LaborerRegistration.this, LaborerStatusPage.class));
            }
        });
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
                startActivity(new Intent(LaborerRegistrationResponseResponse.this, AboutUs.class));
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
