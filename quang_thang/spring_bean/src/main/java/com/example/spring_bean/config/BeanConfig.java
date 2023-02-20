package com.example.spring_bean.config;

import com.example.spring_bean.BeanWithAnnotation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
   //   @Bean("beanWithAnnotation")
   @Bean("beanWithAnnotation")
   BeanWithAnnotation getBean() {
      return new BeanWithAnnotation("bean-annotation-name-example");
   }
}
