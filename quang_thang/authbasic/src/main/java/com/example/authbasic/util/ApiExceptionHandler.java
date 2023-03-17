package com.example.authbasic.util;

import com.example.authbasic.model.ErrorMessageModel;
import jakarta.persistence.EntityNotFoundException;
import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

// @RestControllerAdvice
public class ApiExceptionHandler {
  /** Tất cả các Exception không được khai báo sẽ được xử lý tại đây */
  @ExceptionHandler(Exception.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorMessageModel handleAllException(Exception ex, WebRequest request) {
    return new ErrorMessageModel(new Date(), "Validation fails", ex.getLocalizedMessage());
  }

  /** IndexOutOfBoundsException sẽ được xử lý riêng tại đây */
  @ExceptionHandler(IndexOutOfBoundsException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorMessageModel TodoException(Exception ex, WebRequest request) {
    return new ErrorMessageModel(new Date(), "not exist 2", ex.getLocalizedMessage());
  }

  @ExceptionHandler(EntityNotFoundException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorMessageModel userExistException(Exception e, WebRequest request) {
    return new ErrorMessageModel(new Date(), "not exist 2", e.getLocalizedMessage());
  }
}
