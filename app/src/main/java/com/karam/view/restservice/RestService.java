package com.karam.view.restservice;

import com.karam.db.pojo.Contractor;
import com.karam.db.pojo.ErrorResponse;
import com.karam.db.pojo.Laborer;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RestService {
    @POST("/v1.0/person/laborer")
    public Call<ErrorResponse> createLaborer(@Body Laborer laborer);

    @POST("/v1.0/person/contractor")
    public Call<ErrorResponse> createContractor(@Body Contractor contractor);

    @GET("/v1.0/person/laborer")
    public Call<Laborer> getLaborer(@Query("skills") String [] skills, @Query("locations") String [] locations);
}