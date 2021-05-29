package com.jackrutorial.androidloginrestfulwebserviceexample.remote;

public class ApiUtils {

    public static final String BASE_URL = "https://iiatimd-stephan-jeroen.herokuapp.com/api/"; //base url must end with '/'

    public static UserService getUserService(){
        return RetrofitClient.getClient(BASE_URL).create(UserService.class);
    }

}
