package com.example.dependencyinjection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
 class DependencyInjectionApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext context =  SpringApplication.run(DependencyInjectionApplication.class, args);
    Car myCar = context.getBean(Car.class);
//    myCar.setEngine(new JapanEngine());
    System.out.println(myCar.getEngine().run());
  }
}
