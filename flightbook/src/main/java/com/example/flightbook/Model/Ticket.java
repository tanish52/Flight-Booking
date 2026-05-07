package com.example.flightbook.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Generated;

@Entity
public class Ticket {
  

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  int id;

  int ticketNumber;
  String issueDate;

  @OneToOne
  @JsonBackReference("booking-ticket")
  private Booking booking;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getTicketNumber() {
    return ticketNumber;
  }

  public void setTicketNumber(int ticketNumber) {
    this.ticketNumber = ticketNumber;
  }

  public String getIssueDate() {
    return issueDate;
  }

  public void setIssueDate(String issueDate) {
    this.issueDate = issueDate;
  }

  public Booking getBooking() {
    return booking;
  }

  public void setBooking(Booking booking) {
    this.booking = booking;
  }

  public Ticket(int id, int ticketNumber, String issueDate, Booking booking) {
    this.id = id;
    this.ticketNumber = ticketNumber;
    this.issueDate = issueDate;
    this.booking = booking;
  }

  public Ticket() {
  }


}