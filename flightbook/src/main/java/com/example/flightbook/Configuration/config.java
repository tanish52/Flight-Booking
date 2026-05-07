package com.example.flightbook.Configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.flightbook.Service.UserService;


@Configuration
public class config {

  @Autowired
  jwtfilters jwtFilter;



  
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

    return http
        .csrf(req -> req.disable())
        .formLogin(req -> req.disable())
        .httpBasic(req -> req.disable())
        .sessionManagement(req -> req.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
        .authorizeHttpRequests(req -> req
            .requestMatchers("/api/login", "/api/register").permitAll()
            .requestMatchers("/api/flightregister", "/api/deleteflight/{id}", "/api/getallPassengers").hasAuthority("ADMIN")
            .anyRequest().authenticated())
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
        .oauth2Login(oauth -> oauth.defaultSuccessUrl("/api/Oauthlogin", true))
        .build();
  }


  
  @Bean
  public PasswordEncoder psv(){
    return new BCryptPasswordEncoder();
  
  }


    @Bean
  public AuthenticationManager am(AuthenticationConfiguration auth) throws Exception{
    return auth.getAuthenticationManager();
  }

  @Autowired
  private UserService userService;

  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider(UserService userService){
    DaoAuthenticationProvider provider=new DaoAuthenticationProvider(userService);
    provider.setPasswordEncoder(psv());
    return provider;
  }

}
