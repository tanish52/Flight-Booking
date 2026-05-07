package com.example.flightbook.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.flightbook.Model.UserModel;

public interface Userrepo extends JpaRepository<UserModel,Integer>{
  
  UserModel findByEmail(String email);
}
