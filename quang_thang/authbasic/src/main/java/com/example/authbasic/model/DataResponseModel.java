package com.example.authbasic.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DataResponseModel {
  private final Date timestamp;
  private final Integer statusCode;
  private final String message;
  private final Object errors;
  private final Object data;

  public static DataResponseModel success(
      Date timestamp, int statusCode, String message, Object data) {
    return new DataResponseModel(timestamp, statusCode, message, null, data);
  }

  public static DataResponseModel success(int statusCode, String message, Object data) {
    return new DataResponseModel(new Date(), statusCode, message, null, data);
  }

  public static DataResponseModel error(
      Date timestamp, int statusCode, String message, Object errors) {
    return new DataResponseModel(timestamp, statusCode, message, errors, null);
  }

  public static DataResponseModel error(int statusCode, String message, Object errors) {
    return new DataResponseModel(new Date(), statusCode, message, errors, null);
  }
}
