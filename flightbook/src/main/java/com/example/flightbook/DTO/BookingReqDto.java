package com.example.flightbook.DTO;

import java.util.List;

import com.example.flightbook.Model.Passenger;

import lombok.Data;

@Data
public class BookingReqDto {
  
  private Integer flight;
  private Integer user;
  private List<PassengerDto> passengers;
}
