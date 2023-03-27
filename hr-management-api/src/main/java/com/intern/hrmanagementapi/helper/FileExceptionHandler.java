package com.intern.hrmanagementapi.helper;

import com.intern.hrmanagementapi.constant.MessageConst;
import com.intern.hrmanagementapi.model.DataResponseDto;
import java.io.FileNotFoundException;
import java.nio.file.AccessDeniedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class FileExceptionHandler {

  @ExceptionHandler(FileNotFoundException.class)
  public ResponseEntity<?> handleFileNotFound(FileNotFoundException ex) {
    return ResponseEntity.badRequest().body(
        DataResponseDto.error(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(),
            MessageConst.FILE_NOT_EXIST));
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException ex) {
    return ResponseEntity.badRequest().body(
        DataResponseDto.error(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(),
            MessageConst.FILE_ACCESS_DENIED));
  }

//  @ExceptionHandler(IOException.class)
//  public ResponseEntity<?> handleReadFileError() {
//    return ResponseEntity.badRequest().body(
//        DataResponseDto.error(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(),
//            MessageConst.FILE_READ_ERROR));
//  }
}
