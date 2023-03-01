package com.example.spring_data_jpa.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.*;

@RestController
@RequestMapping("*")
public class NotfoundController {
  @GetMapping
  public String index() {
    try {
      throw new RuntimeException("Error Occurred");
    } catch (RuntimeException e) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "HTTP Status will be NOT FOUND (CODE 404)\n");
    }
    //    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This endpoint unavailable");
  }
}
