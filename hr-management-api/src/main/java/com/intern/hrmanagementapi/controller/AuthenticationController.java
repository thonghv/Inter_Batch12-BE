package com.intern.hrmanagementapi.controller;

import com.intern.hrmanagementapi.Model.AuthenticationRequest;
import com.intern.hrmanagementapi.Model.DataResponse;
import com.intern.hrmanagementapi.Model.RegisterRequest;
import com.intern.hrmanagementapi.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  @PostMapping("/register")
  public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest req) {
    var response = authenticationService.register(req);
    return ResponseEntity.ok(DataResponse.success(200, "Success", response));
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@Valid @RequestBody AuthenticationRequest req) {
    var response = authenticationService.authenticate(req);
    return ResponseEntity.ok(DataResponse.success(200, "Success", response));
  }
}
