package com.example.flightbook.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Notification {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  String message;
  String createdAt;

  @OneToOne
  @JsonBackReference("user-notifications")
  private UserModel user;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public UserModel getUser() {
    return user;
  }

  public void setUser(UserModel user) {
    this.user = user;
  }

  public Notification(int id, String message, String createdAt, UserModel user) {
    this.id = id;
    this.message = message;
    this.createdAt = createdAt;
    this.user = user;
  }

  public Notification() {
  }

}
