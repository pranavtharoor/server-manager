package com.pranavtharoor.servermanager;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddServerData {

    @SerializedName("ip")
    @Expose
    String ip;

    @SerializedName("domain")
    @Expose
    String domain;

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
}
