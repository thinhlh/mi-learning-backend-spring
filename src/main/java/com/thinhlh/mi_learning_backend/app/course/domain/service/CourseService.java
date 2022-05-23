package com.thinhlh.mi_learning_backend.app.course.domain.service;

import com.thinhlh.mi_learning_backend.app.course.domain.entity.Course;

import java.util.List;

public interface CourseService {

    List<Course> getAllCourses();

    Course createCourse(Course course);
}
