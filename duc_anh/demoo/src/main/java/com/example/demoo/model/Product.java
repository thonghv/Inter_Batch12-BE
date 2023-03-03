package com.example.demoo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity(name = "product")
public class Product {
  @Column
  private String id;
  @Column
  private String name;
  @Column
  private Double price;
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }


  public Product(){

  }

  public Product(String id, String name, Double price) {
    this.id = id;
    this.name = name;
    this.price = price;
  }
}
