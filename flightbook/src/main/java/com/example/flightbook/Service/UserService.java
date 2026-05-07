package com.example.flightbook.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.flightbook.Model.UserModel;
import com.example.flightbook.Repository.Userrepo;

@Service
public class UserService implements UserDetailsService {
  @Autowired
  Userrepo userrepo;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserModel user= userrepo.findByEmail(username);
    if(user==null){
      throw new UsernameNotFoundException("User not found");
    }
      String email=user.getEmail();
      String password=user.getPassword();
      List<GrantedAuthority> li=new ArrayList<>();
      li.add(new SimpleGrantedAuthority(user.getRole()));
      return new User(username, password, li);
  }
  
}
