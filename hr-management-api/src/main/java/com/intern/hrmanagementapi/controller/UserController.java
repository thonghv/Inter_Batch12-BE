package com.intern.hrmanagementapi.controller;

import com.intern.hrmanagementapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

  @Autowired
  UserService userService;

  @GetMapping
  public ResponseEntity<?> getAllUser() {
    return ResponseEntity.ok(userService.getAllUser());
  }
}
