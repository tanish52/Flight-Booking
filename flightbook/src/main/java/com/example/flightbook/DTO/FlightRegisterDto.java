package com.example.flightbook.DTO;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class FlightRegisterDto {
  
  @Column(unique = true)
  private String flightNumber;
  @Column(nullable = false)
  private String source;

  @Column(nullable = false)
    private String destination;

    @Column(nullable = false)
    private String departureTime;
    @Column(nullable = false)
    private String arrivalTime;
   @Column(nullable = false)  
   private int totalSeats;
    @Column(nullable = false)
   private int availableSeats;
}
