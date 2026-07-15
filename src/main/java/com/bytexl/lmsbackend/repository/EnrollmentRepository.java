package com.bytexl.lmsbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bytexl.lmsbackend.entity.Course;
import com.bytexl.lmsbackend.entity.Enrollment;
import com.bytexl.lmsbackend.entity.User;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    boolean existsByStudentAndCourse(User student, Course course);

    List<Enrollment> findByStudent(User student);

}
