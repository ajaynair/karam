package com.karam.view.activity.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.karam.rest.RestService;
import com.karam.rest.RestServiceInterface;
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
    Button register;
    UserData userData;

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
        register = findViewById(R.id.register);
        userData = new UserData(this);

        assignListenerToViews();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.contractor_registration;
    }

    private void send_rest_request() {
        RestService retro = new RestService(getApplicationContext());
        RestServiceInterface service = retro.getService();

        Contractor contractor = new Contractor(name.getText().toString(), address.getText().toString(), phone.getText().toString());
        Call<Registration> callSync = service.registerAsContractor(contractor);
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
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send_rest_request();
                userData.setUserStateContractor();
                startActivity(new Intent(ContractorRegistration.this, ContractorPostLogin.class));
            }
        });
    }

    /**
     * Set up menu options
     *
     * @param menu: Menu options (https://pasteboard.co/Jc4U58s.png) to be shown in the view.activity
     * @return: true on no error
     @Override public boolean onCreateOptionsMenu(Menu menu) {
     // Inflate the menu; this adds items to the action bar if it is present.
     getMenuInflater().inflate(R.menu.menu_logged_out, menu);
     return true;
     }

      * Responds to menu option (https://pasteboard.co/Jc4U58s.png) of this view.activity
      *
      * @param item: The item in the menu that is selected
     * @return: return false in case of error, true otherwise
     @Override public boolean onOptionsItemSelected(MenuItem item) {
     switch (item.getItemId()) {
     case (R.id.about_us):
     startActivity(new Intent(ContractorRegistration.this, AboutUs.class));
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
