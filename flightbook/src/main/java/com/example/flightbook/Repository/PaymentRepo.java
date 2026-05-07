package com.example.flightbook.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.flightbook.Model.Booking;
import com.example.flightbook.Model.Payment;

public interface PaymentRepo extends JpaRepository<Payment,Integer> {

  Payment findByBooking_Id(Integer bookingId);

}
