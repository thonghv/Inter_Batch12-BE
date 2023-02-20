package com.example.springbean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:beans.xml")
public class SpringbeanApplication {
   public static void main(String[] args) {
      ConfigurableApplicationContext context = SpringApplication.run(SpringbeanApplication.class, args);
      
      BeanWithXml beanXml = context.getBean(BeanWithXml.class);
      beanXml.showName();
      
      BeanWithAnnotation proService = (BeanWithAnnotation) context.getBean("productService");
      System.out.println(proService.getDescription());
   }
   
}
