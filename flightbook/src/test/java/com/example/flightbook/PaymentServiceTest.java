package com.example.flightbook;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.flightbook.Model.Booking;
import com.example.flightbook.Model.Passenger;
import com.example.flightbook.Model.Payment;
import com.example.flightbook.Model.UserModel;
import com.example.flightbook.Repository.BookingRepo;
import com.example.flightbook.Repository.PassengerRepo;
import com.example.flightbook.Repository.PaymentRepo;
import com.example.flightbook.Service.NotificationService;
import com.example.flightbook.Service.PaymentService;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @InjectMocks
    private PaymentService paymentService;

    @Mock
    private BookingRepo bookingRepo;

    @Mock
    private PassengerRepo passengerrepo;

    @Mock
    private PaymentRepo paymentRepo;

    @Mock
    private NotificationService notificationService;

    @Test
    void testPaymentAlreadyDone() {
        when(paymentRepo.findByBooking_Id(1)).thenReturn(new Payment());

        String result = paymentService.processPayment(1, 5000);

        assertTrue(result.contains("already done"));
    }

    @Test
    void testPaymentSuccess() {
        Booking b = new Booking();
        UserModel u = new UserModel();
        u.setEmail("test@gmail.com");
        b.setUser(u);

        Passenger p = new Passenger();

        when(bookingRepo.findById(1)).thenReturn(Optional.of(b));
        when(passengerrepo.findByBookingid(b)).thenReturn(List.of(p));
        when(paymentRepo.findByBooking_Id(1)).thenReturn(null);

        String result = paymentService.processPayment(1, 5000);

        assertTrue(result.contains("successful"));

        verify(notificationService, times(1))
                .sendEmail(any(), anyInt(), anyInt());
    }
}
