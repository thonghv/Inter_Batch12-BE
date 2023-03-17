package com.example.todoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.example.todoapi.entity"})
public class TodoapiApplication {

  public static void main(String[] args) {
    SpringApplication.run(TodoapiApplication.class, args);
  }
}
