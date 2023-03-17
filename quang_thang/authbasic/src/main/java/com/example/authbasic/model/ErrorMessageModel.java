package com.example.authbasic.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ErrorMessageModel {
  private Date timestamp;
  private String message;
  private String details;
}
