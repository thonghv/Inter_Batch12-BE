package com.intern.hrmanagementapi.exception;

import java.util.UUID;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(UUID id) {
        super("Employee with id " + id + " not found");
    }
}