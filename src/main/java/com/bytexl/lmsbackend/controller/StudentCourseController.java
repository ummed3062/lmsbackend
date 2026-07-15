package com.bytexl.lmsbackend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bytexl.lmsbackend.dto.CourseResponse;
import com.bytexl.lmsbackend.service.CourseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/student/courses")
@RequiredArgsConstructor
public class StudentCourseController {

    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<List<CourseResponse>> getAllCourses() {

        return ResponseEntity.ok(courseService.getAllCourses());

    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> getCourseById(
            @PathVariable Long id) {

        return ResponseEntity.ok(courseService.getCourseById(id));

    }

}
