package com.bytexl.lmsbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bytexl.lmsbackend.dto.AuthResponse;
import com.bytexl.lmsbackend.dto.LoginRequest;
import com.bytexl.lmsbackend.dto.RegisterRequest;
import com.bytexl.lmsbackend.entity.User;
import com.bytexl.lmsbackend.service.AuthService;
import com.bytexl.lmsbackend.service.UserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin("http://localhost:5173/")
public class AuthController {

    @Autowired
    private UserService service;

    @Autowired
    private AuthService authService;

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public String register(
            @RequestBody RegisterRequest request) {

        return service.register(request);

    }

    @PostMapping("/login")
    public AuthResponse login(
            @RequestBody LoginRequest request) {

        return authService.login(request);

    }

    @GetMapping("/getRegisteredUser")
    public List<User> registeredUser() {
        return userService.registeredUsers();
    }


    @GetMapping("/check")
    public String check(){
        return "Hi Ummed this side...";
    }

}
