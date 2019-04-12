package com.pranavtharoor.servermanager;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


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
    public void onBindViewHolder(ServerViewHolder holder, int position) {
        holder.txtServerIp.setText(dataList.get(position).getIp());
        holder.txtServerDomain.setText(dataList.get(position).getDomain());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ServerViewHolder extends RecyclerView.ViewHolder {

        TextView txtServerIp, txtServerDomain;

        ServerViewHolder(View itemView) {
            super(itemView);
            txtServerDomain = (TextView) itemView.findViewById(R.id.server_domain);
            txtServerIp = (TextView) itemView.findViewById(R.id.server_ip);
        }
    }
}