package com.example.spring_data_jpa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/** The type Notfound controller. */
@RestController
@RequestMapping(value = {"*", "*/*"})
public class NotfoundController {
  /**
   * Index string.
   *
   * @return the string
   */
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
