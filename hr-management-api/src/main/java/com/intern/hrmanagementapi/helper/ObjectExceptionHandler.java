package com.intern.hrmanagementapi.helper;

import com.intern.hrmanagementapi.exception.ObjectExistsException;
import com.intern.hrmanagementapi.exception.ObjectNotFoundException;
import com.intern.hrmanagementapi.model.DataResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ObjectExceptionHandler {

  @ExceptionHandler(ObjectExistsException.class)
  public ResponseEntity<?> existedException(ObjectExistsException ex) {
    return ResponseEntity.badRequest().body(
        DataResponseDto.error(ex.getHttpStatus().value(), ex.getHttpStatus().name(),
            ex.getError()));
  }

  @ExceptionHandler(ObjectNotFoundException.class)
  public ResponseEntity<?> notFoundException(ObjectNotFoundException ex) {
    return ResponseEntity.badRequest().body(
        DataResponseDto.error(ex.getHttpStatus().value(), ex.getHttpStatus().name(),
            ex.getError()));
  }
}
