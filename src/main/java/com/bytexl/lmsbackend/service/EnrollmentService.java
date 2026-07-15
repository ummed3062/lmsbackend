package com.bytexl.lmsbackend.service;

import java.util.List;

import com.bytexl.lmsbackend.dto.EnrollmentResponse;

public interface EnrollmentService {

    void enroll(Long courseId);

    List<EnrollmentResponse> getMyCourses();

}
