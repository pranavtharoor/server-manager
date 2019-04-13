package com.pranavtharoor.servermanager;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {

    @POST("login")
    Call<Status> login(@Body UserData data);
}
