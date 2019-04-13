package com.pranavtharoor.servermanager;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserServerDataService {
    @GET("requests")
    Call<List<UserServer>> getRequestData();

    @GET("accesses")
    Call<List<UserServer>> getAccessData();

    @GET("requests/accept/{id}")
    Call<Status> acceptRequest(@Path("id") String id);

    @GET("requests/reject/{id}")
    Call<Status> rejectRequest(@Path("id") String id);

    @GET("accesses/remove/{id}")
    Call<Status> revokeAccess(@Path("id") String id);

}