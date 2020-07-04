package com.karam.view.restservice;

import com.karam.db.pojo.Contractor;
import com.karam.db.pojo.Credentials;
import com.karam.db.pojo.ErrorResponse;
import com.karam.db.pojo.Laborer;
import com.karam.db.pojo.LoginResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestService {
    @POST("/v1.0/person/laborer")
    public Call<ErrorResponse> createLaborer(@Body Laborer laborer);

    @POST("/v1.0/person/session")
    public Call<LoginResponse> createSession(@Body Credentials credentials);

    @POST("/v1.0/person/contractor")
    public Call<ErrorResponse> createContractor(@Body Contractor contractor);

    @GET("/v1.0/person/laborer")
    public Call<ArrayList<Laborer>> getLaborers(@Query("skills") String[] skills, @Query("locations") String[] locations);

    @GET("/v1.0/person/laborer/{pid}/laborer")
    public Call<ArrayList<Laborer>> getLaborerFriends(@Path("pid") int pid);

    @PUT("/v1.0/person/laborer/{pid}")
    public Call<ErrorResponse> putLaborers(@Path("pid") int pid, @Query("status") int status);
}