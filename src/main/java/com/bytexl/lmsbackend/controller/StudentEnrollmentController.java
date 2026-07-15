package com.bytexl.lmsbackend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bytexl.lmsbackend.dto.EnrollmentResponse;
import com.bytexl.lmsbackend.service.EnrollmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentEnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping("/courses/{courseId}/enroll")
    public ResponseEntity<String> enroll(
            @PathVariable Long courseId) {

        enrollmentService.enroll(courseId);

        return ResponseEntity.ok("Course enrolled successfully.");

    }

    @GetMapping("/enrollments")
    public ResponseEntity<List<EnrollmentResponse>> getMyCourses() {

        return ResponseEntity.ok(
                enrollmentService.getMyCourses());

    }

}
