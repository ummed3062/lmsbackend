package com.bytexl.lmsbackend.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {


   private static final String SECRET = "mySecretKeyForJwtAuthenticationmySecretKeyForJwtAuthentication";


   private SecretKey getKey() {
       return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
   }


   public String generateToken(String username) {


       return Jwts.builder()
               .subject(username)
               .issuedAt(new Date())
               .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
               .signWith(getKey())
               .compact();
   }


   public String extractUsername(String token) {
       return extractAllClaims(token).getSubject();
   }


   private Claims extractAllClaims(String token) {
       return Jwts.parser()
               .verifyWith(getKey())
               .build()
               .parseSignedClaims(token)
               .getPayload();
   }


   public boolean isTokenValid(String token, UserDetails userDetails) {


       String username = extractUsername(token);


       return username.equals(userDetails.getUsername())
               && !isTokenExpired(token);
   }


   private boolean isTokenExpired(String token) {
       return extractAllClaims(token)
               .getExpiration()
               .before(new Date());
   }
}

