package com.example.flightbook.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDto {
  
  @Column(nullable = false)
  private String name;
  @Column(unique = true)
  private String email;

  @Column(nullable = false)
  private String password;
  
  

}
