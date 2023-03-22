package com.intern.hrmanagementapi.controller;

import com.intern.hrmanagementapi.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.awt.desktop.UserSessionListener;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
  @Autowired
  UserService userService;
  @GetMapping
  public ResponseEntity<?> getAllUser() {
    Logger logger = LogManager.getLogger(UserController.class);
    logger.info("call api get /users");
    return ResponseEntity.ok(userService.getAllUser());
  }

}
