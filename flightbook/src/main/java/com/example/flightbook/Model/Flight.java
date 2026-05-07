package com.example.flightbook.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Flight {
  
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int id;
  
  @Column(unique = true,nullable = false)
  private String flightNumber;
  @Column(nullable = false)
  private String source;
  @Column(nullable = false)
  private  String destination;
  @Column(nullable = false)
  private String departureTime;
  @Column(nullable = false)
  private  String arrivalTime;
  @Column(nullable = false)
  private int totalSeats;
  
  @Column(nullable = false)
  private  int availableSeats;

  @OneToMany(mappedBy = "flight",cascade = CascadeType.ALL)
  @JsonIgnore
  private List<Booking> bookings;

  public Flight(int id, String flightNumber, String source, String destination, String departureTime,
      String arrivalTime, int totalSeats, int availableSeats, List<Booking> bookings) {
    this.id = id;
    this.flightNumber = flightNumber;
    this.source = source;
    this.destination = destination;
    this.departureTime = departureTime;
    this.arrivalTime = arrivalTime;
    this.totalSeats = totalSeats;
    this.availableSeats = availableSeats;
    this.bookings = bookings;
  }

  public List<Booking> getBookings() {
    return bookings;
  }

  public void setBookings(List<Booking> bookings) {
    this.bookings = bookings;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFlightNumber() {
    return flightNumber;
  }

  public void setFlightNumber(String flightNumber) {
    this.flightNumber = flightNumber;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public String getDestination() {
    return destination;
  }

  public void setDestination(String destination) {
    this.destination = destination;
  }

  public String getDepartureTime() {
    return departureTime;
  }

  public void setDepartureTime(String departureTime) {
    this.departureTime = departureTime;
  }

  public String getArrivalTime() {
    return arrivalTime;
  }

  public void setArrivalTime(String arrivalTime) {
    this.arrivalTime = arrivalTime;
  }

  public int getTotalSeats() {
    return totalSeats;
  }

  public void setTotalSeats(int totalSeats) {
    this.totalSeats = totalSeats;
  }

  public int getAvailableSeats() {
    return availableSeats;
  }

  public void setAvailableSeats(int availableSeats) {
    this.availableSeats = availableSeats;
  }

  public Flight(int id, String flightNumber, String source, String destination, String departureTime,
      String arrivalTime, int totalSeats, int availableSeats) {
    this.id = id;
    this.flightNumber = flightNumber;
    this.source = source;
    this.destination = destination;
    this.departureTime = departureTime;
    this.arrivalTime = arrivalTime;
    this.totalSeats = totalSeats;
    this.availableSeats = availableSeats;
  }

  public Flight() {
  }
}
