package com.example.dependencyinjection;

import org.springframework.stereotype.Component;

@Component
public class JapanEngine implements IEngine {


  public String run() {
    return "This is Japan Engine";
  }
}
