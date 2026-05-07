package com.example.flightbook.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.flightbook.Model.Booking;
import com.example.flightbook.Model.Passenger;
import com.example.flightbook.Model.Payment;
import com.example.flightbook.Model.UserModel;
import com.example.flightbook.Repository.BookingRepo;
import com.example.flightbook.Repository.PassengerRepo;
import com.example.flightbook.Repository.PaymentRepo;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService {
  
  @Autowired
  BookingRepo bookingRepo;

  @Autowired
  PassengerRepo passengerrepo;

  @Autowired
  PaymentRepo paymentRepo;

  @Autowired
  NotificationService notificationService;
  public String processPayment(Integer bookingId, int amount) {
   Booking b= bookingRepo.findById(bookingId).orElse(null);
   List<Passenger> passengers= passengerrepo.findByBookingid(b);
   
    if (amount >= 5000 * passengers.size()) {
        Payment existing = paymentRepo.findByBooking_Id(bookingId);
        if(existing != null){
            return "Payment already done for booking ID: " + bookingId;
        }
      
      if(b!=null){
        b.setStatus("Confirmed");
        bookingRepo.save(b);}
      
      Payment payment = new Payment();
      payment.setBooking(b);
      payment.setAmount(passengers.size()*5000);
      payment.setPaymentDate(LocalDateTime.now().toString());
      payment.setStatus("Success");
      paymentRepo.save(payment);
      UserModel u=b.getUser();
      notificationService.sendEmail(u.getEmail(), bookingId, amount);
      return "Payment successful for booking ID: " + bookingId;
    } else {
        if(b!=null){
          b.setStatus("Failed");
          bookingRepo.save(b);
        
      return "Payment failed for booking ID: " +bookingId + ". Insufficient amount.";
    }
    return "Booking not found";
  }

  
}

public String getPaymentStatus(Integer bookingId) {
  Payment payment = paymentRepo.findByBooking_Id(bookingId);
  if (payment != null) {
      return "Payment status for booking ID " + bookingId + ": " + payment.getStatus();
  } else {
      return "No payment found for booking ID: " + bookingId;
  }
}



  
}
