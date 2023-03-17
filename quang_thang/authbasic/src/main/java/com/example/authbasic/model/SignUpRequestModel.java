package com.example.authbasic.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SignUpRequestModel {
  @NotEmpty(message = "username can not be blank")
  @Size(min = 5, message = "username requires at least 5 characters")
  private String username;

  @Size(min = 8, message = "password requires at least 8 characters")
  @NotEmpty(message = "password can not be empty")
  @Pattern(
      regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
      message = "password not match pattern")
  private String password;
}
