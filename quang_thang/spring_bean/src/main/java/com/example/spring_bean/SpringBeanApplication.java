package com.example.spring_bean;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:beans.xml")

public class SpringBeanApplication {
   
   public static void main(String[] args) {
      ConfigurableApplicationContext context = SpringApplication.run(SpringBeanApplication.class, args);
      BeanWithXml beanXml = (BeanWithXml) context.getBean("beanWithXml");
      System.out.println(beanXml.getName());
      
      
      BeanWithAnnotation beanAnnotation = (BeanWithAnnotation) context.getBean("beanWithAnnotation");
      System.out.println(beanAnnotation.getName());
   }
   
}
