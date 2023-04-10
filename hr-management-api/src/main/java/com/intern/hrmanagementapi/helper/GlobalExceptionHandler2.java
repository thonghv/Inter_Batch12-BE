package com.intern.hrmanagementapi.helper;

import com.intern.hrmanagementapi.constant.MessageConst;
import com.intern.hrmanagementapi.logging.LoggerManager;
import com.intern.hrmanagementapi.model.DataResponseDto;
import java.io.FileNotFoundException;
import java.nio.file.AccessDeniedException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler2 extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatusCode status, WebRequest request) {

    Map<String, Object> responseBody = new HashMap<>();
    List<Object> errors = ex.getBindingResult().getFieldErrors().stream()
        .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());

    responseBody.put("timestamp", new Date());
    responseBody.put("message", "Bad request");
    responseBody.put("statusCode", HttpStatus.BAD_REQUEST.value());
    responseBody.put("errors", errors);

    LoggerManager.error(errors.toString());

    return ResponseEntity.badRequest().body(responseBody);
  }

  @Override
  protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
      HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status,
      WebRequest request) {

    LoggerManager.warn(String.format("%s: %s", ex.getMessage(), request.getDescription(true)));
    return this.handleExceptionInternal(ex, null, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
      HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    LoggerManager.warn(ex.getMessage());
    return ResponseEntity.badRequest().body(
        DataResponseDto.error(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(),
            ex.getLocalizedMessage()));
  }

  @Override
  protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
      HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatusCode status,
      WebRequest request) {
    LoggerManager.warn(ex.getMessage());

    return this.handleExceptionInternal(ex, null, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(
      HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatusCode status,
      WebRequest request) {
    LoggerManager.warn(ex.getMessage());

    return this.handleExceptionInternal(ex, null, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex,
      HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    LoggerManager.warn(ex.getMessage());

    return this.handleExceptionInternal(ex, null, headers, status, request);
  }

  @Override

  protected ResponseEntity<Object> handleMissingServletRequestParameter(
      MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatusCode status,
      WebRequest request) {
    LoggerManager.warn(ex.getMessage());

    return this.handleExceptionInternal(ex, null, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex,
      HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    ProblemDetail body = this.createProblemDetail(ex, status, "Failed to write request", null, null,
        request);
    LoggerManager.warn(ex.getMessage());

    return this.handleExceptionInternal(ex, body, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestPart(
      MissingServletRequestPartException ex, HttpHeaders headers, HttpStatusCode status,
      WebRequest request) {
    LoggerManager.warn(ex.getMessage());

    return this.handleExceptionInternal(ex, null, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleServletRequestBindingException(
      ServletRequestBindingException ex, HttpHeaders headers, HttpStatusCode status,
      WebRequest request) {
    LoggerManager.warn(ex.getMessage());

    return this.handleExceptionInternal(ex, null, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
      HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    LoggerManager.warn(ex.getMessage());

    return this.handleExceptionInternal(ex, null, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleAsyncRequestTimeoutException(
      AsyncRequestTimeoutException ex, HttpHeaders headers, HttpStatusCode status,
      WebRequest request) {
    LoggerManager.warn(ex.getMessage());

    return this.handleExceptionInternal(ex, null, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleErrorResponseException(ErrorResponseException ex,
      HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    LoggerManager.warn(ex.getMessage());

    return this.handleExceptionInternal(ex, null, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex,
      HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    LoggerManager.warn(ex.getMessage());

    Object[] args = new Object[]{ex.getPropertyName(), ex.getValue()};
    String defaultDetail = "Failed to convert '" + args[0] + "' with value: '" + args[1] + "'";
    ProblemDetail body = this.createProblemDetail(ex, status, defaultDetail, null, args, request);
    return this.handleExceptionInternal(ex, body, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
      HttpStatusCode status, WebRequest request) {
    LoggerManager.warn(ex.getMessage());

    Object[] args = new Object[]{ex.getPropertyName(), ex.getValue()};
    String defaultDetail = "Failed to convert '" + args[0] + "' with value: '" + args[1] + "'";
    String messageCode = ErrorResponse.getDefaultDetailMessageCode(TypeMismatchException.class,
        null);
    ProblemDetail body = this.createProblemDetail(ex, status, defaultDetail, messageCode, args,
        request);
    return this.handleExceptionInternal(ex, body, headers, status, request);
  }

  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<?> handleAuthenticationException(AuthenticationException ex) {
    LoggerManager.error(ex.getMessage());

    return ResponseEntity.badRequest().body(
        DataResponseDto.error(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(),
            MessageConst.File.NOT_EXIST));
  }

  @ExceptionHandler(AuthorizationServiceException.class)
  public ResponseEntity<?> handleAuthorizationServiceException(AuthorizationServiceException ex) {
    LoggerManager.error(ex.getMessage());

    return ResponseEntity.badRequest().body(
        DataResponseDto.error(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(),
            MessageConst.File.ACCESS_DENIED));
  }


  @ExceptionHandler(FileNotFoundException.class)
  public ResponseEntity<?> handleFileNotFound(FileNotFoundException ex) {
    LoggerManager.error(ex.getMessage());

    return ResponseEntity.badRequest().body(
        DataResponseDto.error(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(),
            MessageConst.File.NOT_EXIST));
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException ex) {
    LoggerManager.error(ex.getMessage());

    return ResponseEntity.badRequest().body(
        DataResponseDto.error(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(),
            MessageConst.File.ACCESS_DENIED));
  }


  @ExceptionHandler(HttpClientErrorException.class)
  public ResponseEntity<?> handleHttpClientErrorException(HttpClientErrorException ex) {
    LoggerManager.error(ex.getMessage());
    return ResponseEntity.badRequest().body(
        DataResponseDto.error(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(),
            ex.getMessage()));
  }

  @ExceptionHandler({HttpServerErrorException.class})
  public ResponseEntity<?> handleServerError(HttpServerErrorException ex) {
    LoggerManager.error(ex.getMessage());

    return ResponseEntity.internalServerError().body(
        DataResponseDto.error(HttpStatus.INTERNAL_SERVER_ERROR.value(),
            HttpStatus.INTERNAL_SERVER_ERROR.name(), MessageConst.Server.ERROR));
  }
}
