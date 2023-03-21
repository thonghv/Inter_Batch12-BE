package com.intern.hrmanagementapi.Model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  @NotEmpty(message = "username can not be empty")
  @Size(min = 5, message = "username requires at least 5 characters")
  private String username;
  @NotEmpty
  @Email
  private String email;
  //  @Size(min = 8, message = "password requires at least 8 characters")
  @NotEmpty(message = "password can not be empty")
  @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$#!%*?&])[A-Za-z\\d@#$!%*?&]{8,}$", message = "password requires at least 8 characters and must include lowercase letters, uppercase letters, numbers and special characters")
  private String password;
}
