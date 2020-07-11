package com.karam.rest;

import android.content.Context;
import android.widget.Toast;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * REST client to connect to the the REST server
 */
public class RestClient {
    private RestClientInterface service;

    public RestClient(Context c) {
        try {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

            // TODO NEW_COMER Get the IP from a config
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:5000/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();

            this.service = retrofit.create(RestClientInterface.class);
        } catch (Exception ex) {
            Toast.makeText(c, "Unable to connect to the REST server",
                    Toast.LENGTH_LONG).show();
        }
    }

    public RestClientInterface getService() {
        return this.service;
    }
}