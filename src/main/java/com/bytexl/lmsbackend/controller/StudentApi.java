package com.bytexl.lmsbackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentApi {
    
    @GetMapping("/home")
    String home(){
        return "Home API...";
    }

    @GetMapping("/profile")
    String profile(){
        return "Profile API...";
    }
}
