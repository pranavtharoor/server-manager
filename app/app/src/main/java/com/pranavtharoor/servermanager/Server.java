package com.pranavtharoor.servermanager;

import com.google.gson.annotations.SerializedName;

public class Server {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @SerializedName("id")
    private String id;

    @SerializedName("visible")
    private String visible;

    @SerializedName("ip")
    private String ip;

    @SerializedName("domain")
    private String domain;

    public Server(String id, String visible, String domain, String ip) {
        this.id= id;
        this.visible = visible;
        this.ip = ip;
        this.domain = domain;
    }

}