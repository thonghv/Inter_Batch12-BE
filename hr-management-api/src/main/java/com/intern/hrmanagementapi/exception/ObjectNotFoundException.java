package com.intern.hrmanagementapi.exception;

import java.io.Serial;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class ObjectNotFoundException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 1L;
  private final String error;
  private final HttpStatus httpStatus;
  private Throwable originalError;

}
