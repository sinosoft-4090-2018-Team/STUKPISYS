package com.sinosoft.stukpisys.entity;


public class User {

  private long userId;
  private String name;
  private String password;
  private String role;

  public User() {
    super();
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public User(String name, String password, String role) {
    this.name = name;
    this.password = password;
    this.role = role;
  }
}
