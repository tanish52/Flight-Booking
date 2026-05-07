package com.example.flightbook;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.flightbook.DTO.FlightRegisterDto;
import com.example.flightbook.Model.Flight;
import com.example.flightbook.Repository.Flightrepo;
import com.example.flightbook.Service.FlightService;

@ExtendWith(MockitoExtension.class)
class FlightServiceTest {

    @InjectMocks
    private FlightService flightService;

    @Mock
    private Flightrepo flightrepo;

    @Test
    void testRegisterFlight() {
        FlightRegisterDto dto = new FlightRegisterDto();
        dto.setFlightNumber("AI101");

        flightService.registerFlight(dto);

        verify(flightrepo, times(1)).save(any(Flight.class));
    }

    @Test
    void testDeleteFlight() {
        flightService.deleteFlight(1);
        verify(flightrepo).deleteById(1);
    }

    @Test
    void testAllFlights() {
        when(flightrepo.findAll()).thenReturn(List.of(new Flight()));

        List<Flight> result = flightService.allFlights();

        assertEquals(1, result.size());
    }
}