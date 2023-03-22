package com.intern.hrmanagementapi.model;

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
public class RegisterRequestDto {

  @NotEmpty(message = "Username can not be empty")
  @Size(min = 5, message = "Username requires at least 5 characters")
  private String username;
  @NotEmpty
  @Email
  private String email;
  //  @Size(min = 8, message = "password requires at least 8 characters")
  @NotEmpty(message = "Password can not be empty")
  @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$#!%*?&])[A-Za-z\\d@#$!%*?&]{8,}$", message = "Password requires at least 8 characters and must include lowercase letters, uppercase letters, numbers and special characters")
  private String password;
}
