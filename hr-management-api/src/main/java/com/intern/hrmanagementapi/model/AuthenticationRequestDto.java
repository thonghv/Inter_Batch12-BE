package com.intern.hrmanagementapi.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequestDto {

  @NotEmpty(message = "Email can't empty")
  @Email(message = "Email is invalid")
  private String email;
  @NotEmpty(message = "Password can not be empty")
  @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$#!%*?&])[A-Za-z\\d@#$!%*?&]{8,}$", message = "Password requires at least 8 characters and must include lowercase letters, uppercase letters, numbers and special characters")
  private String password;
}
