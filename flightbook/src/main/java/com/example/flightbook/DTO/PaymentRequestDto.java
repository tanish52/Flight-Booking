package com.example.flightbook.DTO;

import lombok.Data;

@Data
public class PaymentRequestDto {
  
  private Integer bookingId;
    private int amount;
}
