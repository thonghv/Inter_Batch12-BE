package com.example.spring_data_jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/** The type Spring data jpa application. */
@EntityScan(basePackages = {"com.example.spring_data_jpa.entity"})
@SpringBootApplication
public class SpringDataJpaApplication {

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(SpringDataJpaApplication.class, args);
  }
}
