package com.example.demoo.model;

public class Product {



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

  private String id;
  private String name;
  private Double price;

  public Product(){

  }

  public Product(String id, String name, Double price) {
    this.id = id;
    this.name = name;
    this.price = price;
  }
}
