package com.intern.hrmanagementapi.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ChangePasswordRequestDto {

  @NotNull
  @NotEmpty(message = "Current password can not be empty")
  @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$#!%*?&])[A-Za-z\\d@#$!%*?&]{8,}$", message = "Current password requires at least 8 characters and must include lowercase letters, uppercase letters, numbers and special characters")
  private String currentPassword;

  @NotNull
  @NotEmpty(message = "New password can not be empty")
  @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$#!%*?&])[A-Za-z\\d@#$!%*?&]{8,}$", message = "New password requires at least 8 characters and must include lowercase letters, uppercase letters, numbers and special characters")
  private String newPassword;
}
