package com.example.flightbook.Configuration;

import java.io.IOException;
import java.nio.file.attribute.UserDefinedFileAttributeView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.flightbook.Repository.Userrepo;
import com.example.flightbook.Service.UserService;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class jwtfilters extends OncePerRequestFilter{
  
  @Autowired
  Userrepo userrepo;

  @Autowired
  JwtBuilder jwtBuilder;

  @Autowired
  UserService userservice;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    System.out.println("Inside filter");
   ;
String path = request.getRequestURI();



    String username=null;
    String token=request.getHeader("Authorization");
    System.out.println("printed jwt "+ token);
    if(token==null || !token.startsWith("Bearer ")){
      filterChain.doFilter(request, response);
      return;
    }

    String details=token.substring(7);
    System.out.println("details "+ details);
    try{
      username=jwtBuilder.extractUsername(details);
    }
    catch(ExpiredJwtException e){
      System.out.println("Expired JWT");
      filterChain.doFilter(request,response);
      return;
    }
    System.out.println("TOKEN VALID");
      if(username !=null && SecurityContextHolder.getContext().getAuthentication()==null){

        UserDetails userdetails=userservice.loadUserByUsername(username);
        System.out.println(userdetails.getAuthorities().toString());
         if (jwtBuilder.validateToken(details,userdetails)) {
UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userdetails,null,userdetails.getAuthorities());
  authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
  SecurityContextHolder.getContext().setAuthentication(authToken);
      }
  }
   

filterChain.doFilter(request,response);

}
}