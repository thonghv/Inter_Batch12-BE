package com.models;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
@SpringBootApplication
public class Application {
    public static void main(String[] args){
      ApplicationContext applicationContext = SpringApplication.run(Application.class,args);

      Outfit outfit = applicationContext.getBean(Outfit.class);

      System.out.println("Instance: " + outfit);
      outfit.wear();
    }
}
