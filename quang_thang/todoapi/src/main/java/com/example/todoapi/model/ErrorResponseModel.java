package com.example.todoapi.model;

import java.util.Date;

public class ErrorResponseModel {

  private final Date timestamp;
  private final String message;
  private final String errors;
  private final int statusCode;

  public ErrorResponseModel(Date timestamp, int statusCode, String message, String errors) {
    this.timestamp = timestamp;
    this.statusCode = statusCode;
    this.message = message;
    this.errors = errors;
  }

  public String getErrors() {
    return errors;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public String getMessage() {
    return message;
  }
}
