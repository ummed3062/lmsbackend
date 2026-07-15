package com.bytexl.lmsbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bytexl.lmsbackend.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}

