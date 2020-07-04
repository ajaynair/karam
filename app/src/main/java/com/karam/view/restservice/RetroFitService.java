package com.karam.view.restservice;

import android.content.Context;
import android.widget.Toast;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFitService {
    private RestService service;

    public RetroFitService(Context c) {
        try {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:5000/")

                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();

            this.service = retrofit.create(RestService.class);
        } catch (Exception ex) {
            Toast.makeText(c, ex.toString(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }

    public RestService getService() {
        return this.service;
    }
}