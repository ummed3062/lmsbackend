package com.bytexl.lmsbackend.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.bytexl.lmsbackend.dto.EnrollmentResponse;
import com.bytexl.lmsbackend.entity.Course;
import com.bytexl.lmsbackend.entity.Enrollment;
import com.bytexl.lmsbackend.entity.User;
import com.bytexl.lmsbackend.exception.ResourceNotFoundException;
import com.bytexl.lmsbackend.repository.CourseRepository;
import com.bytexl.lmsbackend.repository.EnrollmentRepository;
import com.bytexl.lmsbackend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    @Override
    public void enroll(Long courseId) {

        User student = getCurrentStudent();

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        boolean alreadyEnrolled = enrollmentRepository.existsByStudentAndCourse(student, course);

        if (alreadyEnrolled) {
            throw new RuntimeException("You are already enrolled in this course.");
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrolledAt(LocalDateTime.now());

        enrollmentRepository.save(enrollment);
    }

    @Override
    public List<EnrollmentResponse> getMyCourses() {

        User student = getCurrentStudent();

        List<Enrollment> enrollments = enrollmentRepository.findByStudent(student);

        List<EnrollmentResponse> response = new ArrayList<>();

        for (Enrollment enrollment : enrollments) {

            Course course = enrollment.getCourse();

            EnrollmentResponse dto = new EnrollmentResponse();
            dto.setCourseId(course.getId());
            dto.setTitle(course.getTitle());
            dto.setDescription(course.getDescription());
            dto.setPrice(course.getPrice());
            dto.setDuration(course.getDuration());

            response.add(dto);
        }

        return response;
    }

    // Helper Method
    private User getCurrentStudent() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }
}