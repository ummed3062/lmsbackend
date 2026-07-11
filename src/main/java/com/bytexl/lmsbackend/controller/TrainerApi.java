package com.bytexl.lmsbackend.controller;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trainer")
public class TrainerApi {

    

    @GetMapping("/course")
    String courseCreate() {
        return "Course Created successfully";
    }

    @DeleteMapping("/courseDelete")
    String courseDelete() {
        return "Course Deleted successfully";
    }

    
}
