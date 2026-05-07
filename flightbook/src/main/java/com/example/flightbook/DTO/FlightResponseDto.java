package com.example.flightbook.DTO;

import lombok.Data;

@Data
public class FlightResponseDto {
  
  private String flightNumber;
  private String departure;
  private String arrival;
  private String departureTime;
  private String arrivalTime;
  private double price;
}
