package com.example.flightbook;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.flightbook.Model.Booking;
import com.example.flightbook.Model.Payment;
import com.example.flightbook.Model.Ticket;
import com.example.flightbook.Repository.PaymentRepo;
import com.example.flightbook.Repository.TicketRepo;
import com.example.flightbook.Service.TicketService;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @InjectMocks
    private TicketService ticketService;

    @Mock
    private PaymentRepo paymentRepo;

    @Mock
    private TicketRepo ticketRepo;

    @Test
    void testGenerateTicketSuccess() {
        Payment p = new Payment();
        Booking b = new Booking();
        p.setBooking(b);
        p.setStatus("Success");
        p.setPaymentDate("2026-01-01");

        when(paymentRepo.findByBooking_Id(1)).thenReturn(p);

        String result = ticketService.generateTicket(1);

        assertTrue(result.contains("generated"));
        verify(ticketRepo).save(any(Ticket.class));
    }

    @Test
    void testGenerateTicketNoPayment() {
        when(paymentRepo.findByBooking_Id(1)).thenReturn(null);

        String result = ticketService.generateTicket(1);

        assertTrue(result.contains("No payment"));
    }
}
