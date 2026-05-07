package com.example.flightbook.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.flightbook.Configuration.JwtBuilder;
import com.example.flightbook.DTO.UserLoginDto;
import com.example.flightbook.DTO.UserRegisterDto;
import com.example.flightbook.Model.UserModel;
import com.example.flightbook.Repository.Userrepo;

import lombok.RequiredArgsConstructor;

@Service

public class userserv {
  
  @Autowired
  Userrepo userRepo;

@Autowired
  JwtBuilder jwtbuilder;

  @Autowired
  PasswordEncoder psv;
  @Autowired
  AuthenticationManager authenticationManager;
  public void registerUser(UserRegisterDto user) {

    UserModel u=new UserModel();
    u.setRole("USER");
    u.setEmail(user.getEmail());
    u.setName(user.getName());
    u.setPassword(psv.encode(user.getPassword()));
    if(u.getEmail().endsWith("@chitkara.edu.in")){
      u.setRole("ADMIN");
    }
    userRepo.save(u);

  }

  public String loginUser(UserLoginDto user) {
    Authentication auth=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
   try{
    auth.isAuthenticated();
   }
    catch(BadCredentialsException e){
      return "Invalid credentials";
    }
    return jwtbuilder.GenerateToken(user.getEmail());
}

}
