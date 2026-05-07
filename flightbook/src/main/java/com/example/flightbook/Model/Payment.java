package com.example.flightbook.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Payment {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int id;
  int amount;
  String status;
  String paymentDate;

  @OneToOne
  @JsonBackReference("booking-payment")
  private Booking booking;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getPaymentDate() {
    return paymentDate;
  }

  public void setPaymentDate(String paymentDate) {
    this.paymentDate = paymentDate;
  }

  public Booking getBooking() {
    return booking;
  }

  public void setBooking(Booking booking) {
    this.booking = booking;
  }

  public Payment(int id, int amount, String status, String paymentDate, Booking booking) {
    this.id = id;
    this.amount = amount;
    this.status = status;
    this.paymentDate = paymentDate;
    this.booking = booking;
  }

  public Payment() {
  }
}
