package com.example.api.entity;

public class User {
  private String id;
  private String fullname;
  private String username;
  private String email;

  public User() {}

  public User(String id, String fullname, String username, String email) {
    this.id = id;
    this.fullname = fullname;
    this.username = username;
    this.email = email;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getFullname() {
    return fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
