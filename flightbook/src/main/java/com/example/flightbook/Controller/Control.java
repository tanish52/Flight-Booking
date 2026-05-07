package com.example.flightbook.Controller;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.flightbook.Configuration.JwtBuilder;
import com.example.flightbook.DTO.BookingReqDto;
import com.example.flightbook.DTO.BookingResponseDto;
import com.example.flightbook.DTO.FlightRegisterDto;
import com.example.flightbook.DTO.UserLoginDto;
import com.example.flightbook.DTO.UserRegisterDto;
import com.example.flightbook.Model.Flight;
import com.example.flightbook.Model.Passenger;
import com.example.flightbook.Model.UserModel;
import com.example.flightbook.Repository.Userrepo;
import com.example.flightbook.Service.AuthService;
import com.example.flightbook.Service.BookingService;
import com.example.flightbook.Service.FlightService;
import com.example.flightbook.Service.PassengerService;
import com.example.flightbook.Service.PaymentService;
import com.example.flightbook.Service.TicketService;
import com.example.flightbook.Service.userserv;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class Control {
  
@Autowired
userserv userService;

  @PostMapping("/register")
  public String register( @Valid @RequestBody UserRegisterDto user) {
    userService.registerUser(user);
    return "User registered successfully";
  }

  @PostMapping("/login")
  public String login(@RequestBody UserLoginDto user) {

    return userService.loginUser(user);
  
  }

  @Autowired
  FlightService flightService;
  @PostMapping("/flightregister")
  public String flightregister(@Valid @RequestBody FlightRegisterDto flight) {
    flightService.registerFlight(flight);
    return "Flight registered successfully";
  }


  @DeleteMapping("/deleteflight/{id}")
  public String deleteFlight(@PathVariable int id) {
    flightService.deleteFlight(id);
    return "Flight deleted successfully";
  }

  @GetMapping("/allflights")
  public List<Flight> allFlights() {
    return flightService.allFlights();
  }

  @GetMapping("/flightsearch/{source}/{destination}")
  public List<Flight> flightSearch(@PathVariable String source, @PathVariable String destination) {
    return flightService.flightSearch(source, destination);
  }

  @Autowired
  BookingService bookingService;

  @PostMapping("/bookflight")
  public String bookFlight(@RequestBody BookingReqDto booking) {
    return bookingService.bookFlight(booking);
  }

  @GetMapping("/checkFlight/{id}")
  public BookingResponseDto checkFlight(@PathVariable int id) {
    return bookingService.checkFlight(id);
  }

  @Autowired
  PassengerService passengerService;

  @GetMapping("/getallPassengers")
  public List<Passenger> getAllPassengers() {
    return passengerService.getAllPassengers();
  }

@Autowired
PaymentService  paymentService;

  @PostMapping("/makepayment/{bookingid}/{amount}")
  public String makePayment(@PathVariable int bookingid, @PathVariable int amount) {
    return paymentService.processPayment(bookingid, amount);
  }

  @GetMapping("/getpaymentstatus/{bookingid}")
  public String getPaymentStatus(@PathVariable int bookingid) {
    return paymentService.getPaymentStatus(bookingid);
  }
  @Autowired
  TicketService ticketService;

  @PostMapping("/issueTicket/{bookingid}")
  public String issueTicket(@PathVariable int bookingid) {
    return ticketService.generateTicket(bookingid);
  
}
@Autowired
AuthService authService;

@GetMapping("/Oauthlogin")
public String oauthLogin(Authentication authentication) {
  if(authentication == null){
        return "Authentication failed / null";
    }
    
  return authService.sucess(authentication);

}

@Autowired
Userrepo userrepo;

@Autowired
JwtBuilder jwtBuilder;

@GetMapping("/me")
public Map<String, Object> getMe(HttpServletRequest request) {
  String authHeader = request.getHeader("Authorization");
  if (authHeader == null || !authHeader.startsWith("Bearer ")) {
    return Map.of("error", "No token");
  }
  String token = authHeader.substring(7);
  String email = jwtBuilder.extractUsername(token);
  UserModel user = userrepo.findByEmail(email);
  if (user == null) return Map.of("error", "User not found");
  Map<String, Object> result = new HashMap<>();
  result.put("id", user.getId());
  result.put("name", user.getName());
  result.put("email", user.getEmail());
  result.put("role", user.getRole());
  return result;
}
}