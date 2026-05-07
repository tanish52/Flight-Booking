package com.example.flightbook.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.flightbook.Model.Flight;

public interface Flightrepo extends JpaRepository<Flight,Integer>{
  List<Flight> findBySourceAndDestination(String source, String destination);
}
