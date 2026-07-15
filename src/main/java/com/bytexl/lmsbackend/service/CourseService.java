package com.bytexl.lmsbackend.service;

import java.util.List;

import com.bytexl.lmsbackend.dto.CourseResponse;
import com.bytexl.lmsbackend.dto.CreateCourseRequest;

public interface CourseService {

    CourseResponse createCourse(CreateCourseRequest request);
    List<CourseResponse> getAllCourses();
    CourseResponse getCourseById(Long id);
    void deleteCourse(Long id);
}

