package com.example.dependencyinjection;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class VietnamEngine implements IEngine {
  public String run(){
    return "This is Viet Nam engine";
  }
}
