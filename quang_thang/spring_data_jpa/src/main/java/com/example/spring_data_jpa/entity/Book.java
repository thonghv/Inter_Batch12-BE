package com.example.spring_data_jpa.entity;

import jakarta.persistence.*;

@Entity
public class Book {
  @Id private String Id;
  private String title;

  public Book() {}

  public Book(String id, String title) {
    Id = id;
    this.title = title;
  }

  public String getId() {
    return Id;
  }

  public void setId(String id) {
    Id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
