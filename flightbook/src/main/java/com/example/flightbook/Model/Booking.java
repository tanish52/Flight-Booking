package com.example.flightbook.Model;

import java.util.List;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Booking {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String status;
  private String BookingDate;

  @ManyToOne
  @JsonBackReference("user-bookings")
  @JoinColumn(name="user_id")
  private UserModel user;


  @ManyToOne
  @JoinColumn(name="flight_id")
  private Flight flight;


  @OneToMany(mappedBy = "bookingid",cascade = CascadeType.ALL)
  @JsonManagedReference("booking-passengers")
  private List<Passenger> passengers;

  @OneToOne(mappedBy = "booking",cascade = CascadeType.ALL)
  @JsonManagedReference("booking-payment")
  private Payment payment;

  @OneToOne(mappedBy = "booking",cascade = CascadeType.ALL)
  @JsonManagedReference("booking-ticket")
  private Ticket ticket;

  public Booking(int id, String status, String bookingDate, UserModel user, Flight flight, List<Passenger> passengers,
      Payment payment, Ticket ticket) {
    this.id = id;
    this.status = status;
    BookingDate = bookingDate;
    this.user = user;
    this.flight = flight;
    this.passengers = passengers;
    this.payment = payment;
    this.ticket = ticket;
  }


  public List<Passenger> getPassengers() {
    return passengers;
  }


  public void setPassengers(List<Passenger> passengers) {
    this.passengers = passengers;
  }


  public Payment getPayment() {
    return payment;
  }


  public void setPayment(Payment payment) {
    this.payment = payment;
  }


  public Ticket getTicket() {
    return ticket;
  }


  public void setTicket(Ticket ticket) {
    this.ticket = ticket;
  }


  public int getId() {
    return id;
  }


  public void setId(int id) {
    this.id = id;
  }


  public String getStatus() {
    return status;
  }


  public void setStatus(String status) {
    this.status = status;
  }


  public String getBookingDate() {
    return BookingDate;
  }


  public void setBookingDate(String bookingDate) {
    BookingDate = bookingDate;
  }


  public UserModel getUser() {
    return user;
  }


  public void setUser(UserModel user) {
    this.user = user;
  }


  public Flight getFlight() {
    return flight;
  }


  public void setFlight(Flight flight) {
    this.flight = flight;
  }


  public Booking(int id, String status, String bookingDate, UserModel user, Flight flight) {
    this.id = id;
    this.status = status;
    BookingDate = bookingDate;
    this.user = user;
    this.flight = flight;
  }


  public Booking() {
  }

}
