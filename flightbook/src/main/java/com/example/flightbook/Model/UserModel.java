package com.example.flightbook.Model;




import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Builder

public class UserModel {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(nullable = false)
  private String name;

  @Column(unique = true)  
  private String email;
  @Column(nullable = false)
  private String password;
  private String role;

  @OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
  @JsonManagedReference("user-bookings")
  private List<Booking> bookings;

  @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
  @JsonManagedReference("user-notifications")
  private Notification notification;

  public UserModel(int id, String name, String email, String password, String role, List<Booking> bookings,
      Notification notification) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.password = password;
    this.role = role;
    this.bookings = bookings;
    this.notification = notification;
  }
  public Notification getNotification() {
    return notification;
  }
  public void setNotification(Notification notification) {
    this.notification = notification;
  }
  public UserModel(int id, String name, String email, String password, String role, List<Booking> bookings) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.password = password;
    this.role = role;
    this.bookings = bookings;
  }
  public List<Booking> getBookings() {
    return bookings;
  }
  public void setBookings(List<Booking> bookings) {
    this.bookings = bookings;
  }
  public UserModel() {
  }
  public UserModel(int id, String name, String email, String password, String role) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.password = password;
    this.role = role;
  }
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
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
}
