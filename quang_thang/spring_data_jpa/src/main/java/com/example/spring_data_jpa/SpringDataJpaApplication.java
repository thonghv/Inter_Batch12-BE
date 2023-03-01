package com.example.spring_data_jpa;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.domain.*;

@EntityScan(basePackages = {"com.example.spring_data_jpa.entity"})
@SpringBootApplication
public class SpringDataJpaApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringDataJpaApplication.class, args);
  }
}
