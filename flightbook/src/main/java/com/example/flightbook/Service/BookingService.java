package com.example.flightbook.Service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.flightbook.DTO.BookingReqDto;
import com.example.flightbook.DTO.BookingResponseDto;
import com.example.flightbook.Model.Booking;
import com.example.flightbook.Model.Flight;
import com.example.flightbook.Model.Passenger;
import com.example.flightbook.Model.UserModel;
import com.example.flightbook.Repository.BookingRepo;
import com.example.flightbook.Repository.Flightrepo;
import com.example.flightbook.Repository.Userrepo;

@Service
public class BookingService {
  @Autowired
  BookingRepo bookingrepo;

  @Autowired
  Flightrepo flightrepo;

  @Autowired
  Userrepo userrepo;

  public String bookFlight(BookingReqDto booking) {

    Flight f= flightrepo.findById(booking.getFlight()).orElse(null);
    if(f==null){
      return "Flight not found";
    }
    UserModel u= userrepo.findById(booking.getUser()).orElse(null);
    if(u==null){
      return "User not found";
    }

    Booking b=new Booking();
    b.setStatus("Pending");
    int available=f.getAvailableSeats();
    int requestedSeats=booking.getPassengers().size();
    if(requestedSeats>available){
      return "Not enough seats available";
    }
    else{
      f.setAvailableSeats(available-requestedSeats);
      flightrepo.save(f);
      
    }
    b.setBookingDate(LocalDateTime.now().toString());
    b.setFlight(f);
    b.setUser(u);
    b.setPassengers(booking.getPassengers().stream().map(p->{
      Passenger pass=new Passenger();
      pass.setName(p.getName());
      pass.setAge(p.getAge());
      pass.setSeatNumber(p.getSeatNumber());
      pass.setBookingid(b); 
      return pass;
    }).toList());

    bookingrepo.save(b);
  
    return "BOOKING_SUCCESS:" + b.getId();
  }


  public BookingResponseDto checkFlight(int id) {
    Booking b= bookingrepo.findById(id).orElse(null);
    if(b==null){
      return null;
    }
    BookingResponseDto res=new BookingResponseDto();
    res.setStatus(b.getStatus());
    res.setBookingDate(b.getBookingDate());
    return res;
  }
}
