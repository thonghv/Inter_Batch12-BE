package com.intern.hrmanagementapi.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class FileResponseDto {

  private String name;
  private String url;
  private String type;
  private long size;
  private Date createdAt;
}
