package com.intern.hrmanagementapi.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAddingDto extends UserDto {
    @NotNull(message = "Password cannot be null")
    @NotEmpty(message = "Password cannot be empty")
    private String password;
}
