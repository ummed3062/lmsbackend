package com.bytexl.lmsbackend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bytexl.lmsbackend.dto.CourseResponse;
import com.bytexl.lmsbackend.dto.CreateCourseRequest;
import com.bytexl.lmsbackend.service.CourseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/trainer/courses")
@RequiredArgsConstructor
public class TrainerCourseController {

    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<CourseResponse> createCourse(
           @Valid @RequestBody CreateCourseRequest request) {

        CourseResponse response = courseService.createCourse(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping
    public ResponseEntity<List<CourseResponse>> getAllCourses() {

        return ResponseEntity.ok(courseService.getAllCourses());

    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> getCourseById(
            @PathVariable Long id) {

        return ResponseEntity.ok(courseService.getCourseById(id));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(
            @PathVariable Long id) {

        courseService.deleteCourse(id);

        return ResponseEntity.ok("Course deleted successfully.");

    }

}
