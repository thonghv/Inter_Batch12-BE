package com.example.todoapi.model;

import java.util.Date;

public class SuccessResponseModel {

  private final Date timestamp;
  private final String message;
  private final int statusCode;
  private final Object data;

  public SuccessResponseModel(Date timestamp, int statusCode, String message, Object data) {
    this.timestamp = timestamp;
    this.message = message == "" ? "Success" : message;
    this.statusCode = statusCode;
    this.data = data;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public String getMessage() {
    return message;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public Object getData() {
    return data;
  }
}
