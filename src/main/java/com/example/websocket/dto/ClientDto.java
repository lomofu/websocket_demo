package com.example.websocket.dto;

public class ClientDto {
    private String sendUserId;
    private String sentToUserId;
    private boolean isBroadcast;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(String sendUserId) {
        this.sendUserId = sendUserId;
    }

    public String getSentToUserId() {
        return sentToUserId;
    }

    public void setSentToUserId(String sentToUserId) {
        this.sentToUserId = sentToUserId;
    }

    public boolean isBroadcast() {
        return isBroadcast;
    }

    public void setBroadcast(boolean broadcast) {
        isBroadcast = broadcast;
    }
}
