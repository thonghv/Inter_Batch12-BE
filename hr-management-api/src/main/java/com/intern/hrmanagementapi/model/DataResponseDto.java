package com.intern.hrmanagementapi.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DataResponseDto {

  private final Date timestamp;
  private final Integer statusCode;
  private final String message;
  private final Object errors;
  private final Object data;

  public static DataResponseDto success(Date timestamp, int statusCode, String message,
      Object data) {
    return new DataResponseDto(timestamp, statusCode, message, null, data);
  }

  public static DataResponseDto success(int statusCode, String message, Object data) {
    return new DataResponseDto(new Date(), statusCode, message, null, data);
  }

  public static DataResponseDto error(Date timestamp, int statusCode, String message,
      Object errors) {
    return new DataResponseDto(timestamp, statusCode, message, errors, null);
  }

  public static DataResponseDto error(int statusCode, String message, Object errors) {
    return new DataResponseDto(new Date(), statusCode, message, errors, null);
  }
}
