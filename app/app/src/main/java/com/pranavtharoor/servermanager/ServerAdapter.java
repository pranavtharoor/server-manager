package com.pranavtharoor.servermanager;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;


public class ServerAdapter extends RecyclerView.Adapter<ServerAdapter.ServerViewHolder> {

    private List<Server> dataList;

    public ServerAdapter(List<Server> dataList) {
        this.dataList = dataList;
    }

    @Override
    public ServerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_server, parent, false);
        return new ServerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ServerViewHolder holder, final int position) {
        holder.txtServerIp.setText(dataList.get(position).getIp());
        holder.txtServerDomain.setText(dataList.get(position).getDomain());
        holder.btnServerDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Retrofit.Builder builder = new Retrofit.Builder()
                        .baseUrl("https://at-project.ml/api/")
                        .addConverterFactory(GsonConverterFactory.create());

                Retrofit retrofit = builder.build();

                ServerDataService serverDataService = retrofit.create(ServerDataService.class);

                Call<Status> call = serverDataService.deleteServer(Integer.toString(dataList.get(position).getId()));

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

    class ServerViewHolder extends RecyclerView.ViewHolder {

        TextView txtServerIp, txtServerDomain, btnServerDelete;

        ServerViewHolder(View itemView) {
            super(itemView);
            txtServerDomain = (TextView) itemView.findViewById(R.id.server_domain);
            txtServerIp = (TextView) itemView.findViewById(R.id.server_ip);
            btnServerDelete = (Button) itemView.findViewById(R.id.server_delete_btn);
        }
    }
}