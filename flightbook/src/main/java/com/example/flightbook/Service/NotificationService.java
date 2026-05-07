package com.example.flightbook.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
  
  @Autowired
  JavaMailSender mailSender;
  
  public void sendEmail(String to, Integer bookingId, int amount) {
    SimpleMailMessage message=new SimpleMailMessage();
    message.setTo(to);
    message.setSubject("Payment Successfull Flight Booking");
     message.setText(
            "Hi,\n\n" +
            "Your payment is successful.\n" +
            "Booking ID: " + bookingId + "\n" +
            "Amount Paid: " + amount + "\n\n" +
            "Thank you for booking with us!"
        );

        mailSender.send(message);
  }
}
