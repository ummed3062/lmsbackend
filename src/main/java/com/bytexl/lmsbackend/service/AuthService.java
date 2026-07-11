package com.bytexl.lmsbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.bytexl.lmsbackend.dto.AuthResponse;
import com.bytexl.lmsbackend.dto.LoginRequest;
import com.bytexl.lmsbackend.security.JwtService;

@Service
public class AuthService {

   @Autowired
   AuthenticationManager authenticationManager;

   @Autowired
   JwtService jwtService;


   public AuthResponse login(LoginRequest request) {


       Authentication authentication =
               authenticationManager.authenticate(


                       new UsernamePasswordAuthenticationToken(
                               request.getUsername(),
                               request.getPassword()
                       )
               );
       String token =
               jwtService.generateToken(request.getUsername());


       return new AuthResponse(token);
   }
}

