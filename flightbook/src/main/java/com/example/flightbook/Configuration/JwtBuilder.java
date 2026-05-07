package com.example.flightbook.Configuration;

import java.security.Key;
import java.security.Signature;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Base64.Decoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.flightbook.Service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtBuilder {
  
  @Autowired
  UserService userservice;
   private static final String SECRET = "TmV3U2VjcmV0S2V5Rm9ySldUU2lnb9878kmluZ1B1cnBvc2VzMTIzNDU2Nzg=";


   public String GenerateToken(String username){
    Map<String,Object> claims=new HashMap<>();
    return Jwts.builder().claims(claims).subject(username).issuedAt(new Date(System.currentTimeMillis())).expiration(new Date(System.currentTimeMillis() + 10000 * 60 * 45)).signWith(getSignKey(),SignatureAlgorithm.HS256).compact();
   }


   private Key getSignKey() {

    byte[] keyBytes=Decoders.BASE64.decode(SECRET);
    return Keys.hmacShaKeyFor(keyBytes);
   }

   public String extractUsername(String token){
    return Jwts.parser().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody().getSubject();
   }

   public Boolean validateToken(String token, UserDetails userdetails){
    final String username=extractUsername(token);
    return (username.equals(userdetails.getUsername()) && !isTokenExpired(token));
   


}

 public Boolean isTokenExpired(String token) {
      
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        // Extract and return the expiration claim from the token
        return (Date) Jwts.parser().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody().getExpiration();
    }

}