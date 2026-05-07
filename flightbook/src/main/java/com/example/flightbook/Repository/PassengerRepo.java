package com.example.flightbook.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.flightbook.Model.Booking;
import com.example.flightbook.Model.Passenger;

public interface  PassengerRepo extends JpaRepository<Passenger,Integer> {
  List<Passenger> findByBookingid(Booking b);
}
