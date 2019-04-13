package com.pranavtharoor.servermanager;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ServerDataService {
    @GET("servers")
    Call<List<Server>> getServerData();

    @GET("servers/remove/{id}")
    Call<Status> deleteServer(@Path("id") String id);

    @POST("servers/add")
    Call<Status> addServer(@Body AddServerData data);
}