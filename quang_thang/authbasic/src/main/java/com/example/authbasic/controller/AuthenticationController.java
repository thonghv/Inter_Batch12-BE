package com.example.authbasic.controller;

import com.example.authbasic.entity.User;
import com.example.authbasic.model.DataResponseModel;
import com.example.authbasic.model.SignUpRequestModel;
import com.example.authbasic.repo.UserRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
// @CrossOrigin("http://localhost:8080")
@RequestMapping("/api/v1/auth/")
public class AuthenticationController {

  @Autowired private UserRepo userRepo;
  @Autowired PasswordEncoder passwordEncoder;

  @GetMapping("users")
  public ResponseEntity<?> getAllUsers() {
    return ResponseEntity.ok()
        .body(
            DataResponseModel.success(
                HttpStatus.OK.value(), HttpStatus.OK.name(), userRepo.findAll()));
  }

  @PostMapping("sign-up")
  //  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequestModel req) {
    if (userRepo.existsByUsername(req.getUsername())) {
      return ResponseEntity.badRequest()
          .body(
              DataResponseModel.error(
                  HttpStatus.BAD_REQUEST.value(),
                  HttpStatus.BAD_REQUEST.name(),
                  "Username already exist"));
    }
    User newUser = new User(req.getUsername(), passwordEncoder.encode(req.getPassword()));
    userRepo.save(newUser);
    return ResponseEntity.ok(newUser);
  }
}
