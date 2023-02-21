package com.example.dependencyinjection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Car {

  private IEngine engine;

  private String name;
  @Autowired
  public Car(IEngine engine) {
    this.engine = engine;
  }

  public IEngine getEngine() {
    return engine;
  }

  public void setEngine(IEngine engine) {
    this.engine = engine;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
