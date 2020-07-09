package com.karam.rest;

import com.karam.rest.rest_messages.requests.Contractor;
import com.karam.rest.rest_messages.requests.Credentials;
import com.karam.rest.rest_messages.responses.Error;
import com.karam.rest.rest_messages.requests.Laborer;
import com.karam.rest.rest_messages.responses.Registration;
import com.karam.rest.rest_messages.responses.Session;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestServiceInterface {
    @POST("/v1.0/person/laborer")
    Call<Registration> registerAsLaborer(@Body Laborer laborer);

    @POST("/v1.0/person/contractor")
    Call<Registration> registerAsContractor(@Body Contractor contractor);

    @POST("/v1.0/person/session")
    Call<Session> login(@Body Credentials credentials);

    @GET("/v1.0/person/laborer")
    Call<ArrayList<Laborer>> getLaborerList(@Query("skills") String[] skills, @Query("locations") String[] locations);

    @GET("/v1.0/person/laborer/{pid}/laborer")
    Call<ArrayList<Laborer>> getLaborerAndFriendsProfile(@Path("pid") int pid);

    @PUT("/v1.0/person/laborer/{pid}")
    Call<Error> modifyLaborerInfo(@Path("pid") int pid, @Body Laborer laborer);
}