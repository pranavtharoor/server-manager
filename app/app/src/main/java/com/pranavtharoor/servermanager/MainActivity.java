package com.pranavtharoor.servermanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.login_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserData data = new UserData();
                data.setEmail(((EditText)findViewById(R.id.login_email)).getText().toString());
                data.setPassword(((EditText)findViewById(R.id.login_password)).getText().toString());
                Retrofit.Builder builder = new Retrofit.Builder()
                        .baseUrl("https://at-project.ml/api/")
                        .addConverterFactory(GsonConverterFactory.create());

                Retrofit retrofit = builder.build();

                LoginService loginService = retrofit.create(LoginService.class);

                Call<Status> call = loginService.login(data);

                Log.e("asd", "onClick: " +  data.getPassword());
                if (data.getEmail().equals("") || data.getPassword().equals("")) {
                    Toast.makeText(MainActivity.this, "Invalid", Toast.LENGTH_LONG).show();
                } else {

                    call.enqueue(new Callback<Status>() {
                        @Override
                        public void onResponse(Call<Status> call, Response<Status> response) {
                            Status status = response.body();
                            Log.e("asd", "onResponse: " + status.getStatus() );
                            if (status.getStatus() == 1 || status.getStatus() == 2) {
                                Intent myIntent = new Intent(MainActivity.this, AdminHome.class);
                                startActivity(myIntent);
                            } else {
                                Toast.makeText(MainActivity.this, "Invalid", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Status> call, Throwable t) {
                        }
                    });
                }
            }
        });
    }
}
