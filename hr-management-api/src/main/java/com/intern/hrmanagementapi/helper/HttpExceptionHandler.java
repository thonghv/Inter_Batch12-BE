package com.intern.hrmanagementapi.helper;

import com.intern.hrmanagementapi.constant.MessageConst;
import com.intern.hrmanagementapi.model.DataResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@RestControllerAdvice
public class HttpExceptionHandler {

  @ExceptionHandler(HttpMessageNotWritableException.class)
  public ResponseEntity<?> handleConvertingIntoHttpResponse() {
    return ResponseEntity.badRequest().body(
        DataResponseDto.error(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(),
            MessageConst.FILE_ACCESS_DENIED));
  }

  @ExceptionHandler(HttpClientErrorException.class)
  public ResponseEntity<?> handleHttpClientErrorException(HttpClientErrorException ex) {
    return ResponseEntity.badRequest().body(
        DataResponseDto.error(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(),
            ex.getMessage()));
  }

  @ExceptionHandler({HttpServerErrorException.class})
  public ResponseEntity<?> handleServerError() {
    return ResponseEntity.internalServerError().body(
        DataResponseDto.error(HttpStatus.INTERNAL_SERVER_ERROR.value(),
            HttpStatus.INTERNAL_SERVER_ERROR.name(), MessageConst.Server.ERROR));
  }


}
