package com.example.spring_bean;

public class BeanWithAnnotation {
   private String name;
   public  BeanWithAnnotation(String name) {this.name = name;}
   public String getName() {
      return name;
   }
   
   public void setName(String name) {
      this.name = name;
   }
}
