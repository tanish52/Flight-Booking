package com.example.flightbook.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.flightbook.Model.Payment;
import com.example.flightbook.Model.Ticket;
import com.example.flightbook.Repository.PaymentRepo;
import com.example.flightbook.Repository.TicketRepo;

@Service
public class TicketService {
  @Autowired
  PaymentRepo paymentRepo;

  @Autowired
  TicketRepo ticketRepo;
  public String generateTicket(int bookingId) {
    
    Payment payment = paymentRepo.findByBooking_Id(bookingId);
    if (payment != null && "Success".equals(payment.getStatus())) {
      Ticket ticket = new Ticket();
      ticket.setBooking(payment.getBooking());
      ticket.setIssueDate(payment.getPaymentDate());
      ticket.setTicketNumber(1000 + bookingId);
      ticketRepo.save(ticket);
      return "Ticket generated successfully for booking ID: " + bookingId;
    } else if (payment != null) {
      return "Payment status is not successful for booking ID: " + bookingId;
    
}
      else {
        return "No payment found for booking ID: " + bookingId;
  }
}
}