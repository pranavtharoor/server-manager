package com.pranavtharoor.servermanager;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServerDataService {
    @GET("servers")
    Call<List<Server>> getServerData();
}