package com.intern.hrmanagementapi.model;

import com.intern.hrmanagementapi.type.UserRole;
import com.intern.hrmanagementapi.type.UserState;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private UUID id;
    @NotNull(message = "Email cannot be null")
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email is invalid")
    private String email;
    @NotNull(message = "Role cannot be null")
    private UserRole role;
    @NotNull(message = "Username cannot be null")
    @NotEmpty(message = "Username cannot be empty")
    private String username;
    @NotNull(message = "State cannot be null")
    private UserState state;
    private Date create_date;
    private Date update_date;
}
