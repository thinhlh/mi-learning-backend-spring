package com.thinhlh.mi_learning_backend.app.course.domain.service;

import com.thinhlh.mi_learning_backend.app.course.controller.dto.CourseResponse;
import com.thinhlh.mi_learning_backend.app.course.domain.entity.Course;
import com.thinhlh.mi_learning_backend.app.section.domain.entity.Section;

import java.util.List;
import java.util.Map;

public interface CourseService {

    List<CourseResponse> getAllCourses();

    Course createCourse(Course course);
}
