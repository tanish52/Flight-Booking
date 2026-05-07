package com.example.flightbook;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.flightbook.DTO.BookingReqDto;
import com.example.flightbook.DTO.PassengerDto;
import com.example.flightbook.Model.Flight;
import com.example.flightbook.Model.UserModel;
import com.example.flightbook.Repository.BookingRepo;
import com.example.flightbook.Repository.Flightrepo;
import com.example.flightbook.Repository.Userrepo;
import com.example.flightbook.Service.BookingService;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @InjectMocks
    private BookingService bookingService;

    @Mock
    private BookingRepo bookingrepo;

    @Mock
    private Flightrepo flightrepo;

    @Mock
    private Userrepo userrepo;

    @Test
    void testBookFlight_FlightNotFound() {
        BookingReqDto dto = new BookingReqDto();
        dto.setFlight(1);

        when(flightrepo.findById(1)).thenReturn(Optional.empty());

        String result = bookingService.bookFlight(dto);

        assertEquals("Flight not found", result);
    }

    @Test
    void testBookFlight_UserNotFound() {
        BookingReqDto dto = new BookingReqDto();
        dto.setFlight(1);
        dto.setUser(1);

        Flight f = new Flight();
        f.setAvailableSeats(10);

        when(flightrepo.findById(1)).thenReturn(Optional.of(f));
        when(userrepo.findById(1)).thenReturn(Optional.empty());

        String result = bookingService.bookFlight(dto);

        assertEquals("User not found", result);
    }


}