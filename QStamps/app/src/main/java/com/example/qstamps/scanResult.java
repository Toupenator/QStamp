package com.example.qstamps;

public class scanResult {
    private String sender;
    private String reciever;

    public scanResult(String sender, String reciever){
        this.sender = sender;
        this.reciever = reciever;
    }


    public String getReciever() {
        return reciever;
    }

    public void setReciever(String reciever) {
        this.reciever = reciever;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
