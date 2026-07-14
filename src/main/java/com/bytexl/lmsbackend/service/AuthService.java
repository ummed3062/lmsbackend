package com.bytexl.lmsbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.bytexl.lmsbackend.dto.AuthResponse;
import com.bytexl.lmsbackend.dto.LoginRequest;
import com.bytexl.lmsbackend.entity.User;
import com.bytexl.lmsbackend.repository.UserRepository;
import com.bytexl.lmsbackend.security.JwtService;

@Service
public class AuthService {

        @Autowired
        AuthenticationManager authenticationManager;

        @Autowired
        JwtService jwtService;

        @Autowired
        UserRepository userRepository;

        public AuthResponse login(LoginRequest request) {

                Authentication authentication = authenticationManager.authenticate(

                                new UsernamePasswordAuthenticationToken(
                                                request.getUsername(),
                                                request.getPassword()));
                // Load user from database
                User user = userRepository.findByUsername(request.getUsername())
                                .orElseThrow(() -> new RuntimeException("User not found"));

                // Generate JWT
                String token = jwtService.generateToken(user);

                return new AuthResponse(token);
        }
}
