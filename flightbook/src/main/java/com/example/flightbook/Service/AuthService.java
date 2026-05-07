package com.example.flightbook.Service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.example.flightbook.Configuration.JwtBuilder;
import com.example.flightbook.Model.UserModel;
import com.example.flightbook.Repository.Userrepo;

@Service
public class AuthService {

  @Autowired
  Userrepo Userrepo;

  @Autowired
  JwtBuilder jwtBuilder;

  @Autowired
  PasswordEncoder passwordEncoder;

  public String sucess(Authentication authentication) {
    OAuth2User oauthuser= (OAuth2User) authentication.getPrincipal();
    String email=oauthuser.getAttribute("email");
    String username=oauthuser.getAttribute("login");
    if(email==null){
      email=username+"@github.com";
    }
    UserModel user=Userrepo.findByEmail(email);
    String name=oauthuser.getAttribute("name");
    if(name==null){
      name=username;
    }
    if(user==null){
      UserModel newUser=new UserModel();
      newUser.setEmail(email);
      newUser.setName(name);
      newUser.setPassword(passwordEncoder.encode(UUID.randomUUID().toString()));
      newUser.setRole("USER");
      user=Userrepo.save(newUser);
  }
  return jwtBuilder.GenerateToken(user.getEmail());
  
}
}
