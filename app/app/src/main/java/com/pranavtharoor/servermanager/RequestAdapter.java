package com.pranavtharoor.servermanager;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.UserServerViewHolder> {

    private List<UserServer> dataList;

    public RequestAdapter(List<UserServer> dataList) {
        this.dataList = dataList;
    }

    @Override
    public UserServerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_request, parent, false);
        return new UserServerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserServerViewHolder holder, final int position) {
        holder.txtServerIp.setText(dataList.get(position).getIp());
        holder.txtServerDomain.setText(dataList.get(position).getDomain());
        holder.txtUserName.setText(dataList.get(position).getName());
        holder.txtUserEmail.setText(dataList.get(position).getEmail());
        holder.btnRequestAccept.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Retrofit.Builder builder = new Retrofit.Builder()
                        .baseUrl("https://at-project.ml/api/")
                        .addConverterFactory(GsonConverterFactory.create());

                Retrofit retrofit = builder.build();

                UserServerDataService userServerDataService = retrofit.create(UserServerDataService.class);

                Call<Status> call = userServerDataService.acceptRequest(Integer.toString(dataList.get(position).getId()));

                call.enqueue(new Callback<Status>() {
                    @Override
                    public void onResponse(Call<Status> call, Response<Status> response) {

                    }

                    @Override
                    public void onFailure(Call<Status> call, Throwable t) {
                    }
                });
            }
        });
        holder.btnRequestReject.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Retrofit.Builder builder = new Retrofit.Builder()
                        .baseUrl("https://at-project.ml/api/")
                        .addConverterFactory(GsonConverterFactory.create());

                Retrofit retrofit = builder.build();

                UserServerDataService userServerDataService = retrofit.create(UserServerDataService.class);

                Call<Status> call = userServerDataService.rejectRequest(Integer.toString(dataList.get(position).getId()));

                call.enqueue(new Callback<Status>() {
                    @Override
                    public void onResponse(Call<Status> call, Response<Status> response) {
                    }

                    @Override
                    public void onFailure(Call<Status> call, Throwable t) {
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class UserServerViewHolder extends RecyclerView.ViewHolder {

        TextView txtServerIp, txtServerDomain, txtUserName, txtUserEmail, btnRequestAccept, btnRequestReject;

        UserServerViewHolder(View itemView) {
            super(itemView);
            txtServerDomain = (TextView) itemView.findViewById(R.id.request_domain);
            txtServerIp = (TextView) itemView.findViewById(R.id.request_ip);
            txtUserName = (TextView) itemView.findViewById(R.id.request_name);
            txtUserEmail = (TextView) itemView.findViewById(R.id.request_email);
            btnRequestAccept = (TextView) itemView.findViewById(R.id.request_accept_btn);
            btnRequestReject = (TextView) itemView.findViewById(R.id.request_reject_btn);
        }
    }
}