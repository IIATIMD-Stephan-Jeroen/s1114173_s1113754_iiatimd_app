package com.example.api.service;

import com.example.api.model.Login;
import com.example.api.model.Register;
import com.example.api.model.ApiUser;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserClient {

    @POST("login")
    Call<ApiUser> login(@Body Login login);

    @POST("register")
    Call<ApiUser> register(@Body Register register);

    @GET("user")
    Call<ResponseBody> getUser(@Header("Authorization") String authToken);
}
