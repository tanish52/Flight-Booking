package com.example.flightbook.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.flightbook.DTO.FlightRegisterDto;
import com.example.flightbook.Model.Flight;
import com.example.flightbook.Repository.Flightrepo;

@Service
public class FlightService {
  
  @Autowired
  Flightrepo flightrepo;
  public void registerFlight(FlightRegisterDto flight) {
    Flight newFlight = new Flight();
    newFlight.setFlightNumber(flight.getFlightNumber());
    newFlight.setSource(flight.getSource());
    newFlight.setDestination(flight.getDestination());
    newFlight.setDepartureTime(flight.getDepartureTime());
    newFlight.setArrivalTime(flight.getArrivalTime());
    newFlight.setTotalSeats(flight.getTotalSeats());
    newFlight.setAvailableSeats(flight.getAvailableSeats());
    flightrepo.save(newFlight);

  }


  public void deleteFlight(int id) {
    flightrepo.deleteById(id);
  }

  public List<Flight>allFlights() {
    return flightrepo.findAll();
  }

    public List<Flight> flightSearch(String source, String destination) {
      return flightrepo.findBySourceAndDestination(source, destination);
    }
}