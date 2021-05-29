package com.jackrutorial.androidloginrestfulwebserviceexample.remote;

import com.jackrutorial.androidloginrestfulwebserviceexample.model.ResObj;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {

    @GET("login/{email}/{password}")
    Call login(@Path("email") String email, @Path("password") String password);

}
