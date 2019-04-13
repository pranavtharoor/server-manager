package com.pranavtharoor.servermanager;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddServer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_server);

        findViewById(R.id.add_server_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit.Builder builder = new Retrofit.Builder()
                        .baseUrl("https://at-project.ml/api/")
                        .addConverterFactory(GsonConverterFactory.create());

                Retrofit retrofit = builder.build();

                ServerDataService serverDataService = retrofit.create(ServerDataService.class);

                AddServerData data = new AddServerData();

                EditText ipET = (EditText) (findViewById(R.id.add_server_ip));
                EditText domainET = (EditText) (findViewById(R.id.add_server_domain));
                data.setIp(ipET.getText().toString());
                data.setDomain(domainET.getText().toString());

                Call<Status> call = serverDataService.addServer(data);

                call.enqueue(new Callback<Status>() {
                    @Override
                    public void onResponse(Call<Status> call, Response<Status> response) {
                        getParent().finish();
                    }

                    @Override
                    public void onFailure(Call<Status> call, Throwable t) {

                    }
                });

            }
        });
    }
}
