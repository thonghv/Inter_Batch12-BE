package com.intern.hrmanagementapi.Model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DataResponse {

  private final Date timestamp;
  private final Integer statusCode;
  private final String message;
  private final Object errors;
  private final Object data;

  public static DataResponse success(Date timestamp, int statusCode, String message, Object data) {
    return new DataResponse(timestamp, statusCode, message, null, data);
  }

  public static DataResponse success(int statusCode, String message, Object data) {
    return new DataResponse(new Date(), statusCode, message, null, data);
  }

  public static DataResponse error(Date timestamp, int statusCode, String message, Object errors) {
    return new DataResponse(timestamp, statusCode, message, errors, null);
  }

  public static DataResponse error(int statusCode, String message, Object errors) {
    return new DataResponse(new Date(), statusCode, message, errors, null);
  }
}
