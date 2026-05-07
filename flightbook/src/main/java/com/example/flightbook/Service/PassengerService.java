package com.example.flightbook.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.flightbook.Model.Passenger;
import com.example.flightbook.Repository.PassengerRepo;

@Service
public class PassengerService {
  

  @Autowired
  PassengerRepo passengerRepo;
  public List<Passenger> getAllPassengers() {
    return passengerRepo.findAll();
  }
}
