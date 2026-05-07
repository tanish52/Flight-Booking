package com.example.flightbook.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Passenger {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int id;
  String name;
  int age;
  int seatNumber;

  @ManyToOne
  @JoinColumn(name="booking_id")
  @JsonBackReference("booking-passengers")
  private Booking bookingid;

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

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public int getSeatNumber() {
    return seatNumber;
  }

  public void setSeatNumber(int seatNumber) {
    this.seatNumber = seatNumber;
  }

  public Booking getBookingid() {
    return bookingid;
  }

  public void setBookingid(Booking bookingid) {
    this.bookingid = bookingid;
  }

  public Passenger(int id, String name, int age, int seatNumber, Booking bookingid) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.seatNumber = seatNumber;
    this.bookingid = bookingid;
  }

  public Passenger() {
  }

}
