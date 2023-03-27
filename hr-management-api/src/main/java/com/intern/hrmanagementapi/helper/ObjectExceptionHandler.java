package com.intern.hrmanagementapi.helper;

import com.intern.hrmanagementapi.exception.ObjectException;
import com.intern.hrmanagementapi.model.DataResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ObjectExceptionHandler {

  @ExceptionHandler(ObjectException.class)
  public ResponseEntity<?> handleObjectException(ObjectException ex) {
    return ResponseEntity.badRequest().body(
        DataResponseDto.error(ex.getHttpStatus().value(), ex.getHttpStatus().name(),
            ex.getError()));
  }
}
