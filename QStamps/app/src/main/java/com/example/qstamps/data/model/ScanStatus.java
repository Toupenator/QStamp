package com.example.qstamps.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ScanStatus {

    @SerializedName("sender")
    @Expose
    private String sender;

    @SerializedName("reciever")
    @Expose
    private String reciever;

    @SerializedName("status")
    @Expose
    private String status;

    public String getSender() {
        return sender;
    }

    public String getReciever() {
        return reciever;
    }

    public String getStatus() {
        return status;
    }



    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setReciever(String reciever) {
        this.reciever = reciever;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}