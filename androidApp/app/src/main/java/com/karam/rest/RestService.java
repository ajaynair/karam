package com.karam.rest;

import android.content.Context;
import android.widget.Toast;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestService {
    private RestServiceInterface service;

    public RestService(Context c) {
        try {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

            // TODO NEW_COMER Get the IP from a config
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:5000/")

                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();

            this.service = retrofit.create(RestServiceInterface.class);
        } catch (Exception ex) {
            Toast.makeText(c, ex.toString(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }

    public RestServiceInterface getService() {
        return this.service;
    }
}