package com.example.hrmanagementreportapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("home")
public class HomeController {
  @GetMapping
  public String get() {
    return "HOME CONTROLLER HEHEHE";
  }
}