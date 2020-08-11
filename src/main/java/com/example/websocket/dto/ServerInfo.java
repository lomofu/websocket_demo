package com.example.websocket.dto;

import java.util.List;

/**
 * @author lomofu
 * @desc
 * @create 11/Aug/2020 01:18
 */
public class ServerInfo {
  private final String type = "sync";
  private final int count;
  private final List<String> list;

  public ServerInfo(Builder builder) {
    this.count = builder.count;
    this.list = builder.list;
  }

  public static Builder builder() {
    return new Builder();
  }

  public String getType() {
    return type;
  }

  public int getCount() {
    return count;
  }

  public List<String> getList() {
    return list;
  }

  public static class Builder {
    private int count;
    private List<String> list;

    public Builder count(int count) {
      this.count = count;
      return this;
    }

    public Builder list(List<String> list) {
      this.list = list;
      return this;
    }

    public ServerInfo build() {
      return new ServerInfo(this);
    }
  }
}
