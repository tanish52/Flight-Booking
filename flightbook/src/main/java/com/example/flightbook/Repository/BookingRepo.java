package com.example.flightbook.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.flightbook.Model.Booking;

public interface BookingRepo extends JpaRepository<Booking,Integer> {
  
}
