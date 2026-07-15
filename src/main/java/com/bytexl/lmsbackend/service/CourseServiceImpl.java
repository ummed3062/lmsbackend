package com.bytexl.lmsbackend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bytexl.lmsbackend.dto.CourseResponse;
import com.bytexl.lmsbackend.dto.CreateCourseRequest;
import com.bytexl.lmsbackend.entity.Course;
import com.bytexl.lmsbackend.exception.ResourceNotFoundException;
import com.bytexl.lmsbackend.repository.CourseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public CourseResponse createCourse(CreateCourseRequest request) {

        Course course = new Course();

        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setPrice(request.getPrice());
        course.setDuration(request.getDuration());
        

        Course savedCourse = courseRepository.save(course);

        CourseResponse response = new CourseResponse();

        response.setId(savedCourse.getId());
        response.setTitle(savedCourse.getTitle());
        response.setDescription(savedCourse.getDescription());
        response.setPrice(savedCourse.getPrice());
        response.setDuration(savedCourse.getDuration());

        return response;
    }

    @Override
    public List<CourseResponse> getAllCourses() {

        List<Course> courses = courseRepository.findAll();

        List<CourseResponse> response = new ArrayList<>();

        for (Course course : courses) {

            CourseResponse dto = new CourseResponse();

            dto.setId(course.getId());
            dto.setTitle(course.getTitle());
            dto.setDescription(course.getDescription());
            dto.setPrice(course.getPrice());
            dto.setDuration(course.getDuration());

            response.add(dto);
        }

        return response;
    }

    @Override
    public CourseResponse getCourseById(Long id) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        CourseResponse response = new CourseResponse();

        response.setId(course.getId());
        response.setTitle(course.getTitle());
        response.setDescription(course.getDescription());
        response.setPrice(course.getPrice());
        response.setDuration(course.getDuration());

        return response;
    }

    @Override
    public void deleteCourse(Long id) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        courseRepository.delete(course);

    }

}
