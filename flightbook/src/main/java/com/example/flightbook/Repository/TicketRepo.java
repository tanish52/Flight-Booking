package com.example.flightbook.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.flightbook.Model.Ticket;

public interface TicketRepo extends JpaRepository<Ticket,Integer> {
  
}
