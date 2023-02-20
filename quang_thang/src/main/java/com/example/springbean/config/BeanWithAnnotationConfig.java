package com.example.springbean.config;

import com.example.springbean.BeanWithAnnotation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanWithAnnotationConfig {
   @Bean("productService")
   public BeanWithAnnotation getBean(){
      return new BeanWithAnnotation("Tshirt", "Tshirt for children");
   }
}
