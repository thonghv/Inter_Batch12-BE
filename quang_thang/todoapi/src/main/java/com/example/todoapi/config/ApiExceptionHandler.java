package com.example.todoapi.config;

import com.example.todoapi.model.ErrorResponseModel;
import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(IndexOutOfBoundsException.class)
  public ResponseEntity<?> handleIndexOutOfBounds(Exception e) {
    return ResponseEntity.badRequest()
        .body(new ErrorResponseModel(new Date(), 400, e.getMessage(), ""));
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<?> handleAllException(Exception e) {
    return ResponseEntity.badRequest()
        .body(new ErrorResponseModel(new Date(), 400, e.getMessage(), ""));
  }
}
