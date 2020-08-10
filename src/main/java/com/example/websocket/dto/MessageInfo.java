package com.example.websocket.dto;

/**
 * @author lomofu
 * @desc
 * @create 11/Aug/2020 02:49
 */
public class MessageInfo {
    private final String type = "data";
    private final String from;
    private final String msg;

    public MessageInfo(Builder builder) {
        this.from = builder.from;
        this.msg = builder.msg;
    }

    public String getType() {
        return type;
    }

    public String getFrom() {
        return from;
    }

    public String getMsg() {
        return msg;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String from;
        private String msg;

        public Builder from(String from) {
            this.from = from;
            return this;
        }

        public Builder msg(String msg) {
            this.msg = msg;
            return this;
        }

        public MessageInfo build() {
            return new MessageInfo(this);
        }


    }

}
