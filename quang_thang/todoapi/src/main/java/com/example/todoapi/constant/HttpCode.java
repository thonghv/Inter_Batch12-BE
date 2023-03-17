package com.example.todoapi.constant;

public enum HttpCode {
  OK(200),
  CREATED(201),
  NO_CONTENT(204),
  BAD_REQUEST(400),
  UNAUTHORIZED(401),
  FORBIDDEN(403),
  NOT_FOUND(404),
  INTERNAL_SERVER_ERROR(500);
  private final int value;

  HttpCode(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
